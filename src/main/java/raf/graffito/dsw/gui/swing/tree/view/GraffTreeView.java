package raf.graffito.dsw.gui.swing.tree.view;


import raf.graffito.dsw.controller.SlideController;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.controller.GraffTreeCellEditor;
import raf.graffito.dsw.gui.swing.tree.controller.GraffTreeSelectionListener;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.view.PresentationView;
import raf.graffito.dsw.view.ProjectView;
import raf.graffito.dsw.view.SlideView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class GraffTreeView extends JTree {


    public GraffTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        GraffTreeCellRenderer ruTreeCellRenderer = new GraffTreeCellRenderer();
        addTreeSelectionListener(new GraffTreeSelectionListener());
        setCellEditor(new GraffTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 &&
                        javax.swing.SwingUtilities.isLeftMouseButton(e)) {

                    javax.swing.tree.TreePath path =
                            getPathForLocation(e.getX(), e.getY());
                    if (path == null) return;

                    Object n = path.getLastPathComponent();
                    if (n instanceof GraffTreeItem gti) {
                        onNodeDoubleClick(gti, path);
                    }
                }
            }
        });
    }

    private void onNodeDoubleClick(GraffTreeItem node, javax.swing.tree.TreePath path) {
        if(node.getGraffNode() instanceof Project){
            ProjectView projectView = new ProjectView((Project)node.getGraffNode());
            for(int i = 0; i<MainFrame.getInstance().getTabbedPane().getTabCount(); i++){
                Component c = MainFrame.getInstance().getTabbedPane().getComponentAt(i);
                if(c instanceof ProjectView){
                    if(c.equals(projectView)){
                        return;
                    }
                }
            }


            node.getGraffNode().addSubscriber(projectView);

            MainFrame.getInstance().getTabbedPane().addTab(node.getGraffNode().getIme(), null, projectView, "Osnovne informacije");

            for(Presentation presentation : ((Project) node.getGraffNode()).getListaPrezentacija()){
                PresentationView presentationView = new PresentationView(presentation, projectView.getTabbedPane());
                presentation.addSubscriber(presentationView);

                for(Slide slide : presentation.getListaSlajdova()){
                    SlideController slideController = new SlideController(presentationView, slide);

                    SlideView slideView = new SlideView(slide, slideController);
                    slide.addSubscriber(slideView);
                    presentationView.addSlide(slideView);
                }
                projectView.getTabbedPane().addTab(presentation.getIme(), null, presentationView, "Osnovne informacije");

            }
        }
    }
}

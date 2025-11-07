package raf.graffito.dsw.gui.swing.tree.view;


import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.controller.GraffTreeCellEditor;
import raf.graffito.dsw.gui.swing.tree.controller.GraffTreeSelectionListener;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.view.PresentationView;
import raf.graffito.dsw.view.SlideView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class GraffTreeView extends JTree {


    public GraffTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);  // Setujemo model
        GraffTreeCellRenderer ruTreeCellRenderer = new GraffTreeCellRenderer(); // Kreiramo renderer
        addTreeSelectionListener(new GraffTreeSelectionListener()); // Dodajemo listener za selekciju
        setCellEditor(new GraffTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer); // Postavljamo renderer
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
            for(Presentation presentation : ((Project) node.getGraffNode()).getListaPrezentacija()){
                PresentationView presentationView = new PresentationView(presentation);
                for(Slide slide : presentation.getListaSlajdova()){
                    SlideView slideView = new SlideView(slide);
                    presentationView.addSlide(slideView);
                }
                MainFrame.getInstance().getTabbedPane().addTab(presentation.getIme(), null, presentationView, "Osnovne informacije");

            }


        }
    }
}

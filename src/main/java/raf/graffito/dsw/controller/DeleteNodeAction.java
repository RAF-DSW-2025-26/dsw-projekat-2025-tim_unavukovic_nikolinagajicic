package raf.graffito.dsw.controller;

import raf.graffito.dsw.actions.AbstactGraffAction;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.view.PresentationView;
import raf.graffito.dsw.view.ProjectView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteNodeAction extends AbstactGraffAction {
    public DeleteNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete node");
        putValue(SHORT_DESCRIPTION, "Delete node");
    }

    public void actionPerformed(ActionEvent arg0) {
        GraffTreeItem selected = (GraffTreeItem) MainFrame.getInstance().getGraffTree().getSelectedNode();
        MainFrame.getInstance().getGraffTree().removeNode(selected);

        if(selected.getGraffNode() instanceof Project){
            for(int i = 0; i<MainFrame.getInstance().getTabbedPane().getTabCount(); i++){
                Component c = MainFrame.getInstance().getTabbedPane().getComponentAt(i);
                if(c instanceof ProjectView){
                    if(((ProjectView) c).getProject().equals(selected.getGraffNode())){
                        MainFrame.getInstance().getTabbedPane().removeTabAt(i);
                    }
                }
            }
        }else if(selected.getGraffNode() instanceof Presentation){
            for(int i = 0; i<MainFrame.getInstance().getTabbedPane().getTabCount(); i++){
                Component c = MainFrame.getInstance().getTabbedPane().getComponentAt(i);
                if(c instanceof ProjectView){
                    if(((ProjectView) c).getProject().equals(selected.getGraffNode().getParent())){

                        for(int j = 0; j< ((ProjectView) c).getTabbedPane().getTabCount(); j++){
                            Component presentantionView = ((ProjectView) c).getTabbedPane().getComponentAt(j);
                            if(presentantionView instanceof PresentationView){
                                if(((PresentationView) presentantionView).getPresentation().equals(selected.getGraffNode())){
                                    ((PresentationView) presentantionView).getTabbedPane().removeTabAt(j);
                                }
                            }
                        }

                    }
                }
            }
        }



    }
}

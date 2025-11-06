package raf.graffito.dsw.controller;

import raf.graffito.dsw.actions.AbstactGraffAction;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.factory.GraffNodeStore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddNodeAction extends AbstactGraffAction {
    public AddNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    public void actionPerformed(ActionEvent arg0) {
        GraffTreeItem selected = (GraffTreeItem) MainFrame.getInstance().getGraffTree().getSelectedNode();
        MainFrame.getInstance().getGraffTree().addChild(selected);
    }


}

package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.actions.ActionManager;
import raf.graffito.dsw.controller.AboutUsAction;
import raf.graffito.dsw.controller.ExitAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(ActionManager actionManager) {
        super(HORIZONTAL);
        setFloatable(false);

        add(actionManager.getExitAction());
        add(actionManager.getAboutUsAction());
        add(actionManager.getAddNodeAction());
        add(actionManager.getDeleteNodeAction());
        add(actionManager.getRenameAutorAction());
        add(actionManager.getRenameNaslovAction());

        add(actionManager.getUndoAction());
        add(actionManager.getRedoAction());

    }
}

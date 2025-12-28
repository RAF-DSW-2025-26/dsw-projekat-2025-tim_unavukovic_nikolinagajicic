package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.actions.ActionManager;

import javax.swing.*;

public class StateBar extends JToolBar {
    public StateBar(ActionManager actionManager) {
        super(VERTICAL);
        setFloatable(false);

        add(actionManager.getAddAction());
        add(actionManager.getDeleteAction());
        add(actionManager.getResizeAction());
        add(actionManager.getRotateAction());
        add(actionManager.getZoomAction());
    }
}

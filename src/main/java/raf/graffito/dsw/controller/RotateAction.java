package raf.graffito.dsw.controller;

import raf.graffito.dsw.actions.AbstactGraffAction;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RotateAction extends AbstactGraffAction {

    public RotateAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/aboutUs.png"));
        putValue(NAME, "Rotate");
        putValue(SHORT_DESCRIPTION, "rotate");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().startRotateState();
    }
}

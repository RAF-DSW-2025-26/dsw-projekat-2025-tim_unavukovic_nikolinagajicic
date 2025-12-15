package raf.graffito.dsw.controller;

import raf.graffito.dsw.actions.AbstactGraffAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class OpenFilesAction extends AbstactGraffAction {

    public OpenFilesAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/exit.png"));
        putValue(NAME, "Izaberi slike");
        putValue(SHORT_DESCRIPTION, "Izaberi slike");
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

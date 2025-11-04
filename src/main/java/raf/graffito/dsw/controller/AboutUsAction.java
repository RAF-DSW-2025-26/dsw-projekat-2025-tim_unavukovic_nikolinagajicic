package raf.graffito.dsw.controller;

import raf.graffito.dsw.actions.AbstactGraffAction;
import raf.graffito.dsw.gui.swing.AboutsUsDialog;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.model.TeamData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUsAction extends AbstactGraffAction {

    public AboutUsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, loadIcon("/images/aboutUs.png")); // Postavljanje ikonice
        putValue(NAME, "About Us"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "About US"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AboutsUsDialog aboutsUsDialog = new AboutsUsDialog(MainFrame.getInstance());
        aboutsUsDialog.renderMembers(TeamData.members);
        aboutsUsDialog.pack();
        aboutsUsDialog.setVisible(true);
    }

}

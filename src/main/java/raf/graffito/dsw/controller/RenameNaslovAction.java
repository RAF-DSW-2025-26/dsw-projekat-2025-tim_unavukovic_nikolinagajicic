package raf.graffito.dsw.controller;

import raf.graffito.dsw.actions.AbstactGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.observer.Poruka;
import raf.graffito.dsw.observer.TipPoruke;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameNaslovAction extends AbstactGraffAction {
    public RenameNaslovAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/naslov.png"));
        putValue(NAME, "Rename naslov");
        putValue(SHORT_DESCRIPTION, "Rename naslov");
    }

    public void actionPerformed(ActionEvent arg0) {
        GraffTreeItem selected = (GraffTreeItem) MainFrame.getInstance().getGraffTree().getSelectedNode();

        if(!(selected.getGraffNode() instanceof Project)){
            ApplicationFramework.getInstance().getMessageGenerator().notifyAllSubscribers(new Poruka(TipPoruke.GRESKA, "Ova akcija je dostupna samo za Projekat tip cvora"));
            return;
        }

        String naslov = JOptionPane.showInputDialog(null, "Unesite naslov projekta:", "Unos naslova", JOptionPane.PLAIN_MESSAGE);
        if (naslov != null) {
            naslov = naslov.trim();
            if (!naslov.isEmpty()) {
                ((Project) selected.getGraffNode()).setTitle(naslov);
                System.out.println("Naslov: " + naslov);
            }
        }
    }
}
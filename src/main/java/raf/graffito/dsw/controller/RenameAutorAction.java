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

public class RenameAutorAction extends AbstactGraffAction {
    public RenameAutorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "Rename author");
        putValue(SHORT_DESCRIPTION, "Rename author");
    }

    public void actionPerformed(ActionEvent arg0) {
        GraffTreeItem selected = (GraffTreeItem) MainFrame.getInstance().getGraffTree().getSelectedNode();

        if(!(selected.getGraffNode() instanceof Project)){
            ApplicationFramework.getInstance().getMessageGenerator().notifyAllSubscribers(new Poruka(TipPoruke.GRESKA, "Ova akcija je dostupna samo za Projekat tip cvora"));
            return;
        }

        String autor = JOptionPane.showInputDialog(null, "Unesite naziv autora:", "Unos autora", JOptionPane.PLAIN_MESSAGE);
        if (autor != null) {
            autor = autor.trim();
            if (!autor.isEmpty()) {
                ((Project) selected.getGraffNode()).setAuthor(autor);
                System.out.println("Autor: " + autor);
            }
        }
    }
}

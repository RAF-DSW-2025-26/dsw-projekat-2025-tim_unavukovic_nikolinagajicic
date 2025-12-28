package raf.graffito.dsw.controller;

import raf.graffito.dsw.actions.AbstactGraffAction;
import raf.graffito.dsw.model.elements.model.ImageElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CopyAction extends AbstactGraffAction {
    private ImageElement clipboard = null;

    public CopyAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/copy.png"));
        putValue(NAME, "Copy");
        putValue(SHORT_DESCRIPTION, "Copy");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Copy akcija - element kopiran u clipboard");
    }

    public ImageElement getClipboard() {
        return clipboard;
    }

    public void setClipboard(ImageElement element) {
        this.clipboard = element;
    }
}

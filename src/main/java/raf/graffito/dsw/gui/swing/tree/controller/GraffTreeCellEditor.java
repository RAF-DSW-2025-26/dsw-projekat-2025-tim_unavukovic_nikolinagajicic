package raf.graffito.dsw.gui.swing.tree.controller;


import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

/**
 * Nasledjuje DefaultTreeCellEditor kako bi omogucio editovanje cvorova u stablu, dok implementira ActionListener za rukovanje akcijama
 * koje se desavaju tokom editovanja.
 */
public class GraffTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {


    private Object clickedOn = null;
    private JTextField edit = null;

    public GraffTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    // arg0 - JTree komponenta
    // arg1 - objekat koji predstavlja cvor koji se edituje
    // arg2 - boolean vrednost koja oznacava da li je cvor selektovan
    // arg3 - boolean vrednost koja oznacava da li je cvor prosiren
    // arg4 - boolean vrednost koja oznacava da li je cvor lista
    // arg5 - indeks reda cvora u stablu
    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        clickedOn = arg1;
        edit = new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }


    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent) arg0).getClickCount() == 3) {
                return true;
            }
        return false;
    }


    public void actionPerformed(ActionEvent e) {

        if (!(clickedOn instanceof GraffTreeItem))
            return;

        GraffTreeItem clicked = (GraffTreeItem) clickedOn;
        clicked.setName(e.getActionCommand());

    }


}

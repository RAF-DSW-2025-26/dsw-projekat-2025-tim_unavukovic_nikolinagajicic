package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.GraffNodeComposite;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.observer.Poruka;
import raf.graffito.dsw.observer.TipPoruke;

import javax.swing.*;

public class ProjectFactory extends GraffNodeStore{


    @Override
    public GraffNode createNode(GraffNode parent) {
        int broj = ((GraffNodeComposite) parent).getListaCvorova().size()+1;

        String autor = JOptionPane.showInputDialog(null, "Unesite naziv autora:", "Unos autora", JOptionPane.PLAIN_MESSAGE);
        if (autor != null) {
            autor = autor.trim();
        }

        return new Project("Projekat " + broj, autor, 1, parent);
    }
}

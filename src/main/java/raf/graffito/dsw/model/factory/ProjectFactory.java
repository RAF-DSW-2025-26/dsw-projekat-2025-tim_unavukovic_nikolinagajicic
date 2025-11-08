package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.GraffNodeComposite;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.decorator.ColorDecorator;
import raf.graffito.dsw.observer.Poruka;
import raf.graffito.dsw.observer.TipPoruke;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;

public class ProjectFactory extends GraffNodeStore {


    @Override
    public GraffNode createNode(GraffNode parent) {
        int broj = ((GraffNodeComposite) parent).getListaCvorova().size() + 1;

        JTextField tfAutor = new JTextField();
        JColorChooser chooser = new JColorChooser(Color.WHITE);

        for (AbstractColorChooserPanel p : chooser.getChooserPanels()) {
            if (!"RGB".equals(p.getDisplayName())) {
                chooser.removeChooserPanel(p);
            }
        }
        chooser.setPreviewPanel(new JPanel());

        JPanel top = new JPanel(new BorderLayout(6, 6));
        top.add(new JLabel("Autor:"), BorderLayout.WEST);
        top.add(tfAutor, BorderLayout.CENTER);

        JPanel form = new JPanel(new BorderLayout(8, 8));
        form.add(top, BorderLayout.NORTH);
        form.add(chooser, BorderLayout.CENTER);

        int res = JOptionPane.showConfirmDialog(
                null, form, "Unos autora i boje",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (res != JOptionPane.OK_OPTION) return null; // odustanak

        String autor = tfAutor.getText() == null ? null : tfAutor.getText().trim();
        Color boja = chooser.getColor();

        Project pr = new Project("Projekat " + broj, autor, 1, parent);
        ColorDecorator colorDecorator = new ColorDecorator(pr.getIme(), pr.getParent(), pr);
        colorDecorator.setColor(boja);

        if(ApplicationFramework.getInstance().getDecorators().contains(colorDecorator)) {
            ApplicationFramework.getInstance().getMessageGenerator().notifyAllSubscribers(new Poruka(TipPoruke.GRESKA, "Vec postoji projekat sa tom bojom."));
            return null;
        }

        ApplicationFramework.getInstance().getDecorators().add(colorDecorator);

        return pr;
    }
}

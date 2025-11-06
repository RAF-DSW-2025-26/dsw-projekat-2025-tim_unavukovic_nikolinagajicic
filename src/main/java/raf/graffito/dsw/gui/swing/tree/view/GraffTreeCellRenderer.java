package raf.graffito.dsw.gui.swing.tree.view;

import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

/**
 *  Ovo je klasa koja nasledjuje DefaultTreeCellRenderer i koristi se za prilagodjavanje izgleda cvorova u JTree komponenti.
 *  Metoda getTreeCellRendererComponent je override-ovana kako bi se postavila odgovarajuca ikona za svaki cvor u zavisnosti od tipa MapNode-a koji
 *  cvor predstavlja (ProjectExplorer ili Project).
 */
public class GraffTreeCellRenderer extends DefaultTreeCellRenderer {

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

            super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
            URL imageURL = null;

            if (((GraffTreeItem)value).getGraffNode() instanceof Workspace) {
                imageURL = getClass().getResource("/images/tdiagram.gif");
            }
            else if (((GraffTreeItem)value).getGraffNode() instanceof Project) {
                imageURL = getClass().getResource("/images/tproject.gif");
            }

            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);

            return this;
        }

}



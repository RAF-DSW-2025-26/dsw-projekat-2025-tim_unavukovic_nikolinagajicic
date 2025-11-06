package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.tree.view.GraffTreeView;
import raf.graffito.dsw.model.*;
import raf.graffito.dsw.model.factory.GraffNodeStore;
import raf.graffito.dsw.model.factory.Presentationfactory;
import raf.graffito.dsw.model.factory.ProjectFactory;
import raf.graffito.dsw.model.factory.SlideFactory;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class GraffTreeImplementation implements GraffTree {

    private GraffTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public GraffTreeView generateTree(Workspace workspace){
        GraffTreeItem root = new GraffTreeItem(workspace); // Kreiramo root cvor stabla
        treeModel = new DefaultTreeModel(root); // Kreiramo model stabla sa root cvorom
        treeView = new GraffTreeView(treeModel); // Kreiramo view stabla sa modelom
        return treeView;
    }

    @Override
    public void addChild(GraffTreeItem parent) {

        if (!(parent.getGraffNode() instanceof GraffNodeComposite))
            return;

        GraffNode child = createChild(parent.getGraffNode()); // Kreiramo novi child cvor
        parent.add(new GraffTreeItem(child)); // Dodajemo child cvor u parent cvor u view-u
        ((GraffNodeComposite) parent.getGraffNode()).addChild(child); // Dodajemo child cvor u parent cvor u modelu podataka
        treeView.expandPath(treeView.getSelectionPath()); // Prosirujemo parent cvor u view-u da bi se video novi child
        SwingUtilities.updateComponentTreeUI(treeView); // Osvezavamo view
    }

    private static GraffNodeStore returnGraffNodeStore(GraffNode parent) {

        if (parent instanceof Workspace) return new ProjectFactory();
        else if (parent instanceof Project) return new Presentationfactory();
        else if (parent instanceof Presentation) return new SlideFactory();
        return null;

    }

    @Override
    public GraffTreeItem getSelectedNode() {
        return (GraffTreeItem) treeView.getLastSelectedPathComponent();
    }

    private GraffNode createChild(GraffNode parent) {
        GraffNodeStore graffNodeStore = returnGraffNodeStore(parent);

        return graffNodeStore.createNode(parent);
    }

}

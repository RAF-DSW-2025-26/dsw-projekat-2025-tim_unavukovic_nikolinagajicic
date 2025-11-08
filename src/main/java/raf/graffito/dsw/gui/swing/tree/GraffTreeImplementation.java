package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.tree.view.GraffTreeView;
import raf.graffito.dsw.model.*;
import raf.graffito.dsw.model.factory.GraffNodeStore;
import raf.graffito.dsw.model.factory.Presentationfactory;
import raf.graffito.dsw.model.factory.ProjectFactory;
import raf.graffito.dsw.model.factory.SlideFactory;
import raf.graffito.dsw.observer.Poruka;
import raf.graffito.dsw.observer.TipPoruke;

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

        GraffNode child = createChild(parent.getGraffNode());
        parent.add(new GraffTreeItem(child));
        ((GraffNodeComposite) parent.getGraffNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeNode(GraffTreeItem node) {
        if(node.getGraffNode() instanceof Workspace){
            ApplicationFramework.getInstance().getMessageGenerator().notifyAllSubscribers(new Poruka(TipPoruke.GRESKA, "Workspace ne moze da se obrise"));
            return;
        }
        node.removeFromParent();
        if(node.getGraffNode() instanceof GraffNodeComposite){
            ((GraffNodeComposite) node.getGraffNode()).obrisiListu();
        }
        SwingUtilities.updateComponentTreeUI(treeView);
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

        return graffNodeStore.orderNode(parent);
    }

}

package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.tree.view.GraffTreeView;
import raf.graffito.dsw.model.Workspace;

public interface GraffTree {

    GraffTreeView generateTree(Workspace workspace);
    void addChild(GraffTreeItem parent);
    GraffTreeItem getSelectedNode();
    void removeNode(GraffTreeItem item);
}

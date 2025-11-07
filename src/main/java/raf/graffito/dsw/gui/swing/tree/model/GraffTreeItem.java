package raf.graffito.dsw.gui.swing.tree.model;



import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.observer.Poruka;
import raf.graffito.dsw.observer.TipPoruke;

import javax.swing.tree.DefaultMutableTreeNode;


/**
 * DefaultMutableTreeNode je klasa iz Swing biblioteke koja predstavlja cvor u stablu (JTree).
 * Razlika izmedju DefaultMutableTreeNode i DefaultTreeNode je u tome sto DefaultMutableTreeNode
 * dozvoljava menjanje strukture stabla (dodavanje, uklanjanje cvoreva) nakon sto je stablo kreirano,
 * dok DefaultTreeNode ne dozvoljava takve izmene.
 */
public class GraffTreeItem extends DefaultMutableTreeNode {

    private GraffNode graffNode;
    public GraffTreeItem(GraffNode nodeModel) {
        this.graffNode = nodeModel;
    }

    @Override
    public String toString() {
        return graffNode.getIme();
    }

    public void setName(String name) {
        GraffNode graffNode = getGraffNode().getParent().findByName(name);
        if(graffNode == null) {
            System.out.println("Cao");
            this.graffNode.setIme(name);
        }else{
            ApplicationFramework.getInstance().getMessageGenerator().notifyAllSubscribers(new Poruka(TipPoruke.GRESKA,"Vec postoji cvor sa tim imenom u okviru iste putanje"));
        }
    }

    public GraffNode getGraffNode() {
        return graffNode;
    }

    public void setGraffNode(GraffNode graffNode) {
        this.graffNode = graffNode;
    }
}

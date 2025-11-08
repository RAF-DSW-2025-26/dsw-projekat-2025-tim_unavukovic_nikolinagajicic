package raf.graffito.dsw.model.decorator;

import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.GraffNodeComposite;

public abstract class NodeDecorator extends GraffNode {
    private GraffNodeComposite composite;

    public NodeDecorator(String ime, GraffNode parent, GraffNodeComposite composite) {
        super(ime, parent);
        this.composite = composite;
    }

    @Override
    public GraffNode findByName(String name) {
        return null;
    }

    public GraffNodeComposite getComposite() {
        return composite;
    }

    public void setComposite(GraffNodeComposite composite) {
        this.composite = composite;
    }
}

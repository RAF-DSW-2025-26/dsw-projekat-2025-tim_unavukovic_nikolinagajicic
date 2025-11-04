package raf.graffito.dsw.model;

public abstract class GraffLeaf extends GraffNode{
    public GraffLeaf(String ime, GraffNode parent) {
        super(ime, parent);
    }

    @Override
    public GraffNode findByName(String name) {
        if(this.getIme().equals(name)){
            return this;
        }
        return null;
    }
}

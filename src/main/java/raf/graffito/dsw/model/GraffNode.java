package raf.graffito.dsw.model;

public abstract class GraffNode {

    private GraffNode parent;

    private String ime;

    public GraffNode(String ime, GraffNode parent) {
        this.ime = ime;
        this.parent = parent;
    }

    public abstract GraffNode findByName(String name);

    public GraffNode getParent() {
        return parent;
    }

    public void setParent(GraffNode parent) {
        this.parent = parent;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}

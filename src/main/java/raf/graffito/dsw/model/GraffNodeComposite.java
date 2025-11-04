package raf.graffito.dsw.model;

import java.util.ArrayList;
import java.util.List;

public abstract class GraffNodeComposite extends GraffNode {

    private List<GraffNode> listaCvorova = new ArrayList<>();

    public GraffNodeComposite(String ime, GraffNode parent) {
        super(ime, parent);
    }

    @Override
    public GraffNode findByName(String name) {
        if(this.getIme().equals(name)){
            return this;
        }
        for(GraffNode graffNode : listaCvorova){
            GraffNode rezultat = graffNode.findByName(name);
            if(rezultat != null && rezultat.getIme().equals(name)){
                return rezultat;
            }
        }
        return null;
    }
}

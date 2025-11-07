package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.GraffNodeComposite;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;

public class Presentationfactory extends GraffNodeStore{

    @Override
    public GraffNode createNode(GraffNode parent) {
        int broj = ((GraffNodeComposite) parent).getListaCvorova().size()+1;
        return new Presentation("Prezentacija " + broj, parent);
    }
}

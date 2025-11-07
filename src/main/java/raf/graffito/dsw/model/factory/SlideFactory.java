package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.GraffNodeComposite;
import raf.graffito.dsw.model.Slide;

public class SlideFactory extends GraffNodeStore{

    @Override
    public GraffNode createNode(GraffNode parent) {
        int broj = ((GraffNodeComposite) parent).getListaCvorova().size()+1;
        return new Slide("Slajd " + broj, parent);
    }
}

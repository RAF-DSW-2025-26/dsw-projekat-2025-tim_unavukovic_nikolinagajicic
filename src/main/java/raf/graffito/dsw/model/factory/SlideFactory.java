package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.Slide;

public class SlideFactory extends GraffNodeStore{
    @Override
    public GraffNode createNode(GraffNode parent) {
        return new Slide("Slajd", parent);
    }
}

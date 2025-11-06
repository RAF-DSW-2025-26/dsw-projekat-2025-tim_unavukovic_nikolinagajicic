package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.Presentation;

public class Presentationfactory extends GraffNodeStore{
    @Override
    public GraffNode createNode(GraffNode parent) {
        return new Presentation("Prezentacija", parent);
    }
}

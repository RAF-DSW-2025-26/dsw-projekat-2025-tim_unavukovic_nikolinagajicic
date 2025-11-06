package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.GraffNode;

public abstract class GraffNodeStore {

    public GraffNode orderPizza(GraffNode parent) {
        GraffNode node;
        node = createNode(parent);

        return node;
    }

    public abstract GraffNode createNode(GraffNode parent);
}

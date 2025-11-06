package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.Project;

public class ProjectFactory extends GraffNodeStore{
    @Override
    public GraffNode createNode(GraffNode parent) {
        return new Project("Projekat", "Autor", 1, parent);
    }
}

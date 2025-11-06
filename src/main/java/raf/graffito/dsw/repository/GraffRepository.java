package raf.graffito.dsw.repository;


import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.GraffNodeComposite;
import raf.graffito.dsw.model.Workspace;

public interface GraffRepository {
    Workspace getWorkSpace();
    void addChild(GraffNodeComposite parent, GraffNode child);
}

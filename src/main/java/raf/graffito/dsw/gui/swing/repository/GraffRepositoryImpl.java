package raf.graffito.dsw.gui.swing.repository;

import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.GraffNodeComposite;
import raf.graffito.dsw.model.Workspace;
import raf.graffito.dsw.repository.GraffRepository;


public class GraffRepositoryImpl implements GraffRepository {

    private Workspace workspace;

    public GraffRepositoryImpl() {
        workspace = new Workspace("My Workspace");
    }

    @Override
    public Workspace getWorkSpace() {
        return workspace;
    }

    @Override
    public void addChild(GraffNodeComposite parent, GraffNode child) {

    }
}

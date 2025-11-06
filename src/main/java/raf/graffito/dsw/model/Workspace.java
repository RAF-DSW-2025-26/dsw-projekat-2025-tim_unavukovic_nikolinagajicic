package raf.graffito.dsw.model;

import java.util.ArrayList;
import java.util.List;

public class Workspace extends GraffNodeComposite{

    private List<Project> listaProjekata= new ArrayList<>();

    public Workspace(String ime) {
        super(ime, null);
    }


    public List<Project> getListaProjekata() {
        return listaProjekata;
    }

    public void setListaProjekata(List<Project> listaProjekata) {
        this.listaProjekata = listaProjekata;
    }

    @Override
    public void addChild(GraffNode child) {
        if(child instanceof Project){
            listaProjekata.add((Project) child);
        }
    }
}

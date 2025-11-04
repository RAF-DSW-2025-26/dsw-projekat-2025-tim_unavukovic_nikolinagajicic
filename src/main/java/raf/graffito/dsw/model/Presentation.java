package raf.graffito.dsw.model;

import java.util.ArrayList;
import java.util.List;

public class Presentation extends GraffNodeComposite {

    private List<Slide> listaSlajdova=new ArrayList<Slide>();

    public Presentation(String ime, GraffNode parent) {
        super(ime, parent);
    }

    public List<Slide> getListaSlajdova() {
        return listaSlajdova;
    }

    public void setListaSlajdova(List<Slide> listaSlajdova) {
        this.listaSlajdova = listaSlajdova;
    }
}

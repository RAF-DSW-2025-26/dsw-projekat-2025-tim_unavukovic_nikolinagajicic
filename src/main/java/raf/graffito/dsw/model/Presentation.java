package raf.graffito.dsw.model;

import raf.graffito.dsw.observer.Publisher;
import raf.graffito.dsw.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Presentation extends GraffNodeComposite {
    private List<Subscriber> subscribers = new ArrayList<>();

    private List<Slide> listaSlajdova=new ArrayList<Slide>();

    public Presentation(String ime, GraffNode parent) {
        super(ime, parent);
    }

    @Override
    public void addChild(GraffNode child) {
        if(child instanceof Slide){
            listaSlajdova.add((Slide) child);
            getListaCvorova().add(child);
        }
    }

    public List<Slide> getListaSlajdova() {
        return listaSlajdova;
    }

    public void setListaSlajdova(List<Slide> listaSlajdova) {
        this.listaSlajdova = listaSlajdova;
    }

}

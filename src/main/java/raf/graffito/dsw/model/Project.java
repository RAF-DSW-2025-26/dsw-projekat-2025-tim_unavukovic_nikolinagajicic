package raf.graffito.dsw.model;

import java.util.ArrayList;
import java.util.List;

public class Project extends GraffNodeComposite {

    private String title,author;
    private int member;

    private List<Presentation> listaPrezentacija = new ArrayList<>();
    private List<Slide> listaSlajdova = new ArrayList<>();

    public Project(String title, String author, int member) {
        this.title = title;
        this.author = author;
        this.member = member;
        listaPrezentacija.add(new Presentation());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Presentation> getListaPrezentacija() {
        return listaPrezentacija;
    }

    public void setListaPrezentacija(List<Presentation> listaPrezentacija) {
        this.listaPrezentacija = listaPrezentacija;
    }

    public List<Slide> getListaSlajdova() {
        return listaSlajdova;
    }

    public void setListaSlajdova(List<Slide> listaSlajdova) {
        this.listaSlajdova = listaSlajdova;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }
}

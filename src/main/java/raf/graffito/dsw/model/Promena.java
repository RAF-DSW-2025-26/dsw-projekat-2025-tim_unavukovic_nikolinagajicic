package raf.graffito.dsw.model;

public class Promena {
    private String naziv;

    private Object object;

    public Promena(String naziv, Object object) {
        this.naziv = naziv;
        this.object = object;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

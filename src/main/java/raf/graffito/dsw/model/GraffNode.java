package raf.graffito.dsw.model;

import raf.graffito.dsw.observer.Publisher;
import raf.graffito.dsw.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public abstract class GraffNode implements Publisher {

    private List<Subscriber> subscribers = new ArrayList<>();

    private GraffNode parent;

    private String ime;

    public GraffNode(String ime, GraffNode parent) {
        this.ime = ime;
        this.parent = parent;
    }

    public abstract GraffNode findByName(String name);

    public GraffNode getParent() {
        return parent;
    }

    public void setParent(GraffNode parent) {
        this.parent = parent;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
        notifyAllSubscribers(this);
    }

    public void  addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }
    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    public void removeAllSubscribers(){
        subscribers.clear();
    }

    public void notifyAllSubscribers(Object object){
        for(Subscriber subscriber : subscribers){
            subscriber.update(object);
        }
    }
}

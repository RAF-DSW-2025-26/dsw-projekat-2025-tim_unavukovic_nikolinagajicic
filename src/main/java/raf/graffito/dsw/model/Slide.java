package raf.graffito.dsw.model;

import raf.graffito.dsw.observer.Publisher;
import raf.graffito.dsw.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Slide extends  GraffLeaf implements Publisher {
    private List<Subscriber> subscriberList = new ArrayList<>();

    public Slide(String ime, GraffNode parent) {
        super(ime, parent);
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscriberList.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }

    @Override
    public void notifyAllSubscribers(Object object) {
        for(Subscriber subscriber : subscriberList) {
            subscriber.update(object);
        }
    }
}

package raf.graffito.dsw.observer;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements Publisher {

    private List<Subscriber> listaSubscribera= new ArrayList<Subscriber>();

    @Override
    public void addSubscriber(Subscriber subscriber) {
        listaSubscribera.add(subscriber);

    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        listaSubscribera.remove(subscriber);

    }

    @Override
    public void notifyAllSubscribers(Object object) {
        for (Subscriber subscriber : listaSubscribera) {
            notifyAllSubscribers(subscriber);
        }

    }
}

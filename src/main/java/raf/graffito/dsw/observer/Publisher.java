package raf.graffito.dsw.observer;

import java.util.List;

public interface Publisher {

    public void  addSubscriber(Subscriber subscriber);
    public void removeSubscriber(Subscriber subscriber);
    public void notifyAllSubscribers(Object object);
}

package raf.graffito.dsw.observer.simpleFactory;

import raf.graffito.dsw.observer.Poruka;
import raf.graffito.dsw.observer.Subscriber;

public interface Logger extends Subscriber {

    public void ispisi(Poruka poruka);
}

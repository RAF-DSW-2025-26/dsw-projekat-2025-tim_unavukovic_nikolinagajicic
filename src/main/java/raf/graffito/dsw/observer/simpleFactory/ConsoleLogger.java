package raf.graffito.dsw.observer.simpleFactory;

import raf.graffito.dsw.observer.Poruka;

public class ConsoleLogger implements Logger {
    @Override
    public void ispisi(Poruka poruka) {
        System.out.println(poruka);
    }

    @Override
    public void update(Object object) {
        if (object instanceof Poruka) {
            ispisi((Poruka) object);
        }
    }
}

package raf.graffito.dsw.observer.simpleFactory;

import raf.graffito.dsw.observer.Poruka;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements Logger{
    @Override
    public void ispisi(Poruka poruka) {
        try {
            PrintWriter pw= new PrintWriter(new FileWriter("log.txt"),true);
            pw.println(poruka);
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Object object) {
        if (object instanceof Poruka) {
            ispisi((Poruka) object);
        }
    }
}

package raf.graffito.dsw.model.elements.view.painter;


import raf.graffito.dsw.model.elements.model.DiagramElement;

import java.awt.*;

public abstract class PrimordialPainter implements Painter {

    public DiagramElement element;
    public Shape oblik;

    public PrimordialPainter(DiagramElement element) {
        this.element = element;
    }

    public boolean elementAt(Point p) {
        return oblik != null && oblik.contains(p);
    }

    public Shape getOblik() {
        return oblik;
    }

    public DiagramElement getElement() {
        return element;
    }
}
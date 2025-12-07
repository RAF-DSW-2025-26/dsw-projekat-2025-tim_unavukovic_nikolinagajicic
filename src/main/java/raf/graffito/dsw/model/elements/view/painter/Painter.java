package raf.graffito.dsw.model.elements.view.painter;

import java.awt.*;

public interface Painter {
    void paint(Graphics2D g);
    boolean elementAt(Point p);
}

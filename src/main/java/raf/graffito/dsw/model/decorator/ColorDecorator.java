package raf.graffito.dsw.model.decorator;

import raf.graffito.dsw.model.GraffNode;
import raf.graffito.dsw.model.GraffNodeComposite;

import java.awt.*;
import java.util.Objects;

public class ColorDecorator extends NodeDecorator{
    private Color color;
    public ColorDecorator(String ime, GraffNode parent, GraffNodeComposite composite) {
        super(ime, parent, composite);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ColorDecorator that = (ColorDecorator) o;
        return Objects.equals(color, that.color);
    }

}

package raf.graffito.dsw.model.elements.model;

import java.awt.*;

public abstract class DiagramElement {
    protected Point lokacija;
    protected Dimension dimenzija;
    private boolean selected = false;
    private double rotationAngle = 0;

    public DiagramElement(Point lokacija, Dimension dimenzija) {
        this.lokacija = lokacija;
        this.dimenzija = dimenzija;
    }

    public Point getLokacija() {
        return lokacija;
    }

    public Dimension getDimenzija() {
        return dimenzija;
    }

    public void pomeri(int dx, int dy) {
        lokacija.translate(dx, dy);
    }

    public boolean isSelected() {
        return selected;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public void setDimenzija(Dimension dimenzija) {
        this.dimenzija = dimenzija;
    }

}

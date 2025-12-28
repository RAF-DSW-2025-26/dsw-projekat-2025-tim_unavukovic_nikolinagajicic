package raf.graffito.dsw.model;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class LogoModel {
    private double x;
    private double y;
    private double scale;
    private double rotation; // u stepenima
    private Color color;

    private List<LogoObserver> observers = new ArrayList<>();

    public LogoModel(double x, double y, double scale, double rotation) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.rotation = rotation;
        this.color = new Color(255, 215, 0); // Zlatna boja
    }

    /**
     * Vraća AffineTransform matricu koja kombinuje sve transformacije
     */
    public AffineTransform getTransformMatrix() {
        AffineTransform matrix = new AffineTransform();
        matrix.translate(x, y);
        matrix.rotate(Math.toRadians(rotation));
        matrix.scale(scale, scale);
        return matrix;
    }

    /**
     * Vraća inverznu matricu za hit detection
     */
    public AffineTransform getInverseTransformMatrix() {
        try {
            return getTransformMatrix().createInverse();
        } catch (Exception e) {
            e.printStackTrace();
            return new AffineTransform();
        }
    }

    // === GETTERS & SETTERS ===

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        notifyObservers();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        notifyObservers();
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        if (scale > 0.1) {
            this.scale = scale;
            notifyObservers();
        }
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
        notifyObservers();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        notifyObservers();
    }

    // === TRANSFORMACIONE OPERACIJE ===

    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
        notifyObservers();
    }

    public void scaleBy(double factor) {
        double newScale = this.scale * factor;
        if (newScale > 0.1 && newScale < 10.0) {
            this.scale = newScale;
            notifyObservers();
        }
    }

    public void rotateBy(double angleDegrees) {
        this.rotation += angleDegrees;
        while (this.rotation >= 360) this.rotation -= 360;
        while (this.rotation < 0) this.rotation += 360;
        notifyObservers();
    }

    // === OBSERVER PATTERN ===

    public void addObserver(LogoObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LogoObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (LogoObserver observer : observers) {
            observer.onLogoChanged(this);
        }
    }

    public interface LogoObserver {
        void onLogoChanged(LogoModel model);
    }
}
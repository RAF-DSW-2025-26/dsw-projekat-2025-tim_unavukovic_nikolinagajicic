package raf.graffito.dsw.model;

import raf.graffito.dsw.observer.Publisher;
import raf.graffito.dsw.observer.Subscriber;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class LogoModel implements Publisher {
    private double x;
    private double y;
    private double scale;
    private double rotation; // u stepenima
    private Color color;

    private List<Subscriber> subscribers = new ArrayList<>();

    public LogoModel(double x, double y, double scale, double rotation) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.rotation = rotation;
        this.color = new Color(255, 215, 0); // Zlatna boja
    }

    public AffineTransform getTransformMatrix() {
        AffineTransform matrix = new AffineTransform();
        matrix.translate(x, y);
        matrix.rotate(Math.toRadians(rotation));
        matrix.scale(scale, scale);
        return matrix;
    }

    public AffineTransform getInverseTransformMatrix() {
        try {
            return getTransformMatrix().createInverse();
        } catch (Exception e) {
            e.printStackTrace();
            return new AffineTransform();
        }
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        notifyAllSubscribers(null);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        notifyAllSubscribers(null);
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        if (scale > 0.1) {
            this.scale = scale;
            notifyAllSubscribers(null);
        }
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
        notifyAllSubscribers(null);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        notifyAllSubscribers(null);
    }


    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
        notifyAllSubscribers(null);

    }

    public void scaleBy(double factor) {
        double newScale = this.scale * factor;
        if (newScale > 0.1 && newScale < 10.0) {
            this.scale = newScale;
            notifyAllSubscribers(null);
        }
    }

    public void rotateBy(double angleDegrees) {
        this.rotation += angleDegrees;
        while (this.rotation >= 360) this.rotation -= 360;
        while (this.rotation < 0) this.rotation += 360;
        notifyAllSubscribers(null);
    }


    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifyAllSubscribers(Object object) {
        for(Subscriber subscriber : subscribers) {
            subscriber.update(object);
        }
    }

}
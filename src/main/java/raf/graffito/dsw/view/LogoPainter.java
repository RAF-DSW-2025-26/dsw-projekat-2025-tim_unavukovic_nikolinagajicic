package raf.graffito.dsw.view;

import raf.graffito.dsw.model.LogoModel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class LogoPainter {
    private LogoModel model;
    private GeneralPath crownShape;

    private static final int HANDLE_SIZE = 10;
    private static final int ROTATION_HANDLE_OFFSET = 35;

    public LogoPainter(LogoModel model) {
        this.model = model;
        initCrownShape();
    }

    private void initCrownShape() {
        crownShape = new GeneralPath();
        crownShape.moveTo(-20, 15);
        crownShape.lineTo(20, 15);
        crownShape.lineTo(25, -5);
        crownShape.lineTo(12, 0);
        crownShape.lineTo(0, -25);
        crownShape.lineTo(-12, 0);
        crownShape.lineTo(-25, -5);
        crownShape.closePath();
    }

    public void paint(Graphics2D g) {
        AffineTransform oldTransform = g.getTransform();
        Color oldColor = g.getColor();
        Stroke oldStroke = g.getStroke();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AffineTransform modelTransform = model.getTransformMatrix();
        g.transform(modelTransform);

        g.setColor(model.getColor());
        g.fill(crownShape);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.draw(crownShape);

        paintHandles(g);

        g.setTransform(oldTransform);
        g.setColor(oldColor);
        g.setStroke(oldStroke);
    }

    private void paintHandles(Graphics2D g) {
        g.setStroke(new BasicStroke(1.5f));
        g.setColor(new Color(0, 120, 255));

        Rectangle2D bounds = crownShape.getBounds2D();
        g.draw(bounds);

        double scaleX = bounds.getMaxX();
        double scaleY = bounds.getMaxY();
        Rectangle2D scaleHandle = new Rectangle2D.Double(
                scaleX - HANDLE_SIZE/2.0,
                scaleY - HANDLE_SIZE/2.0,
                HANDLE_SIZE,
                HANDLE_SIZE
        );
        g.setColor(new Color(255, 165, 0));
        g.fill(scaleHandle);
        g.setColor(Color.BLACK);
        g.draw(scaleHandle);

        double rotX = bounds.getCenterX();
        double rotY = bounds.getMinY() - ROTATION_HANDLE_OFFSET;

        g.setColor(new Color(0, 120, 255));
        g.drawLine((int)bounds.getCenterX(), (int)bounds.getMinY(), (int)rotX, (int)rotY);

        Rectangle2D rotHandle = new Rectangle2D.Double(
                rotX - HANDLE_SIZE/2.0,
                rotY - HANDLE_SIZE/2.0,
                HANDLE_SIZE,
                HANDLE_SIZE
        );
        g.setColor(new Color(50, 205, 50));
        g.fill(rotHandle);
        g.setColor(Color.BLACK);
        g.draw(rotHandle);
    }


    public boolean contains(Point screenPoint) {
        Point2D localPoint = model.getInverseTransformMatrix().transform(screenPoint, null);
        return crownShape.contains(localPoint);
    }

    public boolean isScalingHandle(Point screenPoint) {
        Point2D localPoint = model.getInverseTransformMatrix().transform(screenPoint, null);
        Rectangle2D bounds = crownShape.getBounds2D();

        Rectangle2D handle = new Rectangle2D.Double(
                bounds.getMaxX() - HANDLE_SIZE,
                bounds.getMaxY() - HANDLE_SIZE,
                HANDLE_SIZE * 2,
                HANDLE_SIZE * 2
        );
        return handle.contains(localPoint);
    }

    public boolean isRotationHandle(Point screenPoint) {
        Point2D localPoint = model.getInverseTransformMatrix().transform(screenPoint, null);
        Rectangle2D bounds = crownShape.getBounds2D();

        double rotX = bounds.getCenterX();
        double rotY = bounds.getMinY() - ROTATION_HANDLE_OFFSET;

        Rectangle2D handle = new Rectangle2D.Double(
                rotX - HANDLE_SIZE,
                rotY - HANDLE_SIZE,
                HANDLE_SIZE * 2,
                HANDLE_SIZE * 2
        );
        return handle.contains(localPoint);
    }

    public LogoModel getModel() {
        return model;
    }
}
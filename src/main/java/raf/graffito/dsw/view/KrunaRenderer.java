package raf.graffito.dsw.view;

import raf.graffito.dsw.model.KrunaModel;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class KrunaRenderer {

    public void iscrtaj(Graphics2D g2d, KrunaModel model) {
        var oldTransform = g2d.getTransform();
        Color oldColor = g2d.getColor();
        Stroke oldStroke = g2d.getStroke();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.translate(model.getX(), model.getY());

        g2d.scale(model.getScale(), model.getScale());

        g2d.rotate(Math.toRadians(model.getRotationAngle()));

        GeneralPath kruna = new GeneralPath();
        kruna.moveTo(-20, 15);
        kruna.lineTo(20, 15);
        kruna.lineTo(25, -5);
        kruna.lineTo(12, 5);
        kruna.lineTo(0, -20);
        kruna.lineTo(-12, 5);
        kruna.lineTo(-25, -5);
        kruna.closePath();

        g2d.setColor(new Color(255, 215, 0));
        g2d.fill(kruna);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(kruna);

        g2d.setTransform(oldTransform);
        g2d.setColor(oldColor);
        g2d.setStroke(oldStroke);
    }
}
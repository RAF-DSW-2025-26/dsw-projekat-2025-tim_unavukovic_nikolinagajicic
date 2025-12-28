package raf.graffito.dsw.view;

import raf.graffito.dsw.model.KrunaModel;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class KrunaRenderer {

    public void iscrtaj(Graphics2D g2d, KrunaModel model) {
        // Čuvamo staro stanje (transformaciju i boju)
        var oldTransform = g2d.getTransform();
        Color oldColor = g2d.getColor();
        Stroke oldStroke = g2d.getStroke();

        // Uključujemo Anti-aliasing za glatke ivice
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // --- TRANSFORMACIJE ---
        // 1. Pomeramo koordinatni sistem na poziciju iz modela (gornji levi ugao)
        g2d.translate(model.getX(), model.getY());

        // 2. Skaliramo
        g2d.scale(model.getScale(), model.getScale());

        // 3. Rotiramo
        g2d.rotate(Math.toRadians(model.getRotationAngle()));

        // --- DEFINISANJE OBLIKA (KRUNA) ---
        GeneralPath kruna = new GeneralPath();
        // Crtamo oko centra (0,0) koji je sada pomeren na (x,y)
        kruna.moveTo(-20, 15);   // Donji levi
        kruna.lineTo(20, 15);    // Donji desni
        kruna.lineTo(25, -5);    // Desna ivica
        kruna.lineTo(12, 5);     // Desno udubljenje
        kruna.lineTo(0, -20);    // Srednji špic (Vrh)
        kruna.lineTo(-12, 5);    // Levo udubljenje
        kruna.lineTo(-25, -5);   // Leva ivica
        kruna.closePath();

        // --- ISCRTAVANJE ---
        // Zlatna ispuna
        g2d.setColor(new Color(255, 215, 0));
        g2d.fill(kruna);

        // Crna ivica
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(kruna);

        // Vraćamo grafiku u prvobitno stanje da ne pokvarimo ostatak slajda
        g2d.setTransform(oldTransform);
        g2d.setColor(oldColor);
        g2d.setStroke(oldStroke);
    }
}
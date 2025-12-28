package raf.graffito.dsw.model.elements.controller;


import raf.graffito.dsw.commands.MoveElementCommand;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.model.ImageElement;
import raf.graffito.dsw.model.elements.view.painter.ImagePainter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private boolean dragging = false;
    private Point start;
    private ImagePainter painter;
    private Component panel;

    private Point dragStart;
    private Slide slide;

    public MyMouseListener(ImagePainter painter, Component pane) {
        this.painter = painter;
        this.panel = pane;
        this.slide = slide;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (painter.elementAt(e.getPoint())) {
            dragging = true;
            start = e.getPoint();
        }
        panel.repaint();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!dragging) return;

        int dx = e.getX() - start.x;
        int dy = e.getY() - start.y;

        painter.getElement().pomeri(dx, dy);

        start = e.getPoint();

        ImageElement elem = (ImageElement) painter.getElement();
        painter.oblik = new Rectangle(elem.getLokacija().x,
                elem.getLokacija().y,
                elem.getDimenzija().width,
                elem.getDimenzija().height);

        panel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**@Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        panel.repaint();
    }*/
    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragging) {
            // Kada je pomeranje završeno, kreiramo Command sa ukupnim pomerajem
            int totalDx = e.getX() - dragStart.x;
            int totalDy = e.getY() - dragStart.y;

            if (totalDx != 0 || totalDy != 0) {
                // Prvo vratimo element na početnu poziciju
                painter.getElement().pomeri(-totalDx, -totalDy);

                // Zatim izvršimo pomeranje kroz Command
                MoveElementCommand command = new MoveElementCommand(
                        slide,
                        (ImageElement) painter.getElement(),
                        totalDx,
                        totalDy
                );
                slide.getCommandManager().executeCommand(command);
            }
        }

        dragging = false;
        panel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
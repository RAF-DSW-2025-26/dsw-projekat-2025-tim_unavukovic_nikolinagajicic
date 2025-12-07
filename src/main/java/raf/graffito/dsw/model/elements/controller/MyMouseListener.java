package raf.graffito.dsw.model.elements.controller;


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

    public MyMouseListener(ImagePainter painter, Component pane) {
        this.painter = painter;
        this.panel = pane;
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

        // update painter shape
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

    @Override
    public void mouseReleased(MouseEvent e) {
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
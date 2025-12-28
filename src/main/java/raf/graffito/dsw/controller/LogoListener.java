package raf.graffito.dsw.controller;

import raf.graffito.dsw.model.LogoModel;
import raf.graffito.dsw.view.LogoPainter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogoListener extends MouseAdapter {
    private LogoModel model;
    private LogoPainter painter;
    private Component view;

    private Point lastMousePos;
    private ActionState currentState = ActionState.NONE;

    private enum ActionState {
        NONE, MOVING, SCALING, ROTATING
    }

    public LogoListener(LogoModel model, Component view) {
        this.model = model;
        this.view = view;
        this.painter = new LogoPainter(model);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMousePos = e.getPoint();

        if (painter.isScalingHandle(e.getPoint())) {
            currentState = ActionState.SCALING;
            e.consume();
        } else if (painter.isRotationHandle(e.getPoint())) {
            currentState = ActionState.ROTATING;
            e.consume();
        } else if (painter.contains(e.getPoint())) {
            currentState = ActionState.MOVING;
            e.consume();
        } else {
            currentState = ActionState.NONE;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentState == ActionState.NONE) return;

        int dx = e.getX() - lastMousePos.x;
        int dy = e.getY() - lastMousePos.y;

        switch (currentState) {
            case MOVING:
                handleMoving(dx, dy);
                break;
            case SCALING:
                handleScaling(dx, dy);
                break;
            case ROTATING:
                handleRotating(e.getPoint());
                break;
        }

        lastMousePos = e.getPoint();
        view.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentState != ActionState.NONE) {
            e.consume();
        }
        currentState = ActionState.NONE;
    }

    private void handleMoving(int dx, int dy) {
        model.translate(dx, dy);
    }

    private void handleScaling(int dx, int dy) {
        double scaleFactor = 1.0 + (dx + dy) * 0.01;
        model.scaleBy(scaleFactor);
    }

    private void handleRotating(Point currentPos) {
        double centerX = model.getX();
        double centerY = model.getY();

        double angleCurrent = Math.toDegrees(
                Math.atan2(currentPos.y - centerY, currentPos.x - centerX)
        );
        double anglePrev = Math.toDegrees(
                Math.atan2(lastMousePos.y - centerY, lastMousePos.x - centerX)
        );

        double angleDiff = angleCurrent - anglePrev;
        model.rotateBy(angleDiff);
    }

    public LogoPainter getPainter() {
        return painter;
    }
}


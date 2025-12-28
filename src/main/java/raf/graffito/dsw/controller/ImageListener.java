package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.model.ImageElement;
import raf.graffito.dsw.state.concrete.AddState;
import raf.graffito.dsw.view.LogoPainter;
import raf.graffito.dsw.view.SlideView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageListener extends MouseAdapter {
    private ImageElement imageElement;
    private SlideView slideView;

    public ImageListener(ImageElement imageElement, SlideView slideView) {
        this.imageElement = imageElement;
        this.slideView = slideView;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getStateManager().getCurrent().performOperation(slideView, imageElement);
    }
}

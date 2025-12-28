package raf.graffito.dsw.commands;

import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.model.ImageElement;

import java.awt.*;

public class ResizeElementCommand implements Command {
    private Slide slide;
    private ImageElement element;
    private Dimension previousSize;
    private Dimension newSize;

    public ResizeElementCommand(Slide slide, ImageElement element, Dimension newSize) {
        this.slide = slide;
        this.element = element;
        this.previousSize = new Dimension(element.getDimenzija());
        this.newSize = newSize;
    }

    @Override
    public void execute() {
        element.setDimenzija(newSize);
        slide.notifyAllSubscribers(slide.getDiagramElements());
        System.out.println("Akcija izvršena: Element skaliran na " + newSize.width + "x" + newSize.height);
    }

    @Override
    public void undo() {
        element.setDimenzija(previousSize);
        slide.notifyAllSubscribers(slide.getDiagramElements());
        System.out.println("Akcija poništena: Element vraćen na " + previousSize.width + "x" + previousSize.height);
    }
}

package raf.graffito.dsw.commands;

import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.model.ImageElement;

import java.util.List;

public class MoveMultipleElementsCommand implements Command {
    private Slide slide;
    private List<ImageElement> elements;
    private int dx;
    private int dy;

    public MoveMultipleElementsCommand(Slide slide, List<ImageElement> elements, int dx, int dy) {
        this.slide = slide;
        this.elements = elements;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        for (ImageElement element : elements) {
            element.pomeri(dx, dy);
        }
        slide.notifyAllSubscribers(slide.getDiagramElements());
        System.out.println("Akcija izvršena: " + elements.size() + " elemenata pomereno");
    }

    @Override
    public void undo() {
        for (ImageElement element : elements) {
            element.pomeri(-dx, -dy);
        }
        slide.notifyAllSubscribers(slide.getDiagramElements());
        System.out.println("Akcija poništena: " + elements.size() + " elemenata vraćeno");
    }
}

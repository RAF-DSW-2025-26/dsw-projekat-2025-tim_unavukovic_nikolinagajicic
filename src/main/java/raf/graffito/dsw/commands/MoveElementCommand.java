package raf.graffito.dsw.commands;

import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.model.ImageElement;

public class MoveElementCommand implements Command {
    private Slide slide;
    private ImageElement element;
    private int dx;
    private int dy;

    public MoveElementCommand(Slide slide, ImageElement element, int dx, int dy) {
        this.slide = slide;
        this.element = element;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        element.pomeri(dx, dy);
        slide.notifyAllSubscribers(slide.getDiagramElements());
        System.out.println("Akcija izvršena: Element pomeren za (" + dx + ", " + dy + ")");
    }

    @Override
    public void undo() {
        element.pomeri(-dx, -dy);
        slide.notifyAllSubscribers(slide.getDiagramElements());
        System.out.println("Akcija poništena: Element vraćen za (-" + dx + ", -" + dy + ")");
    }
}

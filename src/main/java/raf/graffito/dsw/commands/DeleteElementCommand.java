package raf.graffito.dsw.commands;

import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.model.ImageElement;

public class DeleteElementCommand implements Command {
    private Slide slide;
    private ImageElement element;

    public DeleteElementCommand(Slide slide, ImageElement element) {
        this.slide = slide;
        this.element = element;
    }

    @Override
    public void execute() {
        slide.removeElementDirect(element);
        System.out.println("Akcija izvršena: Element obrisan sa slajda");
    }

    @Override
    public void undo() {
        slide.addElementDirect(element);
        System.out.println("Akcija poništena: Element vraćen na slajd");
    }
}

package raf.graffito.dsw.commands;

import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.model.ImageElement;

public class AddElementCommand implements Command {
    private Slide slide;
    private ImageElement element;

    public AddElementCommand(Slide slide, ImageElement element) {
        this.slide = slide;
        this.element = element;
    }

    @Override
    public void execute() {
        slide.addElementDirect(element);
        System.out.println("Akcija izvršena: Element dodat na slajd");
    }

    @Override
    public void undo() {
        slide.removeElementDirect(element);
        System.out.println("Akcija poništena: Element uklonjen sa slajda");
    }
}

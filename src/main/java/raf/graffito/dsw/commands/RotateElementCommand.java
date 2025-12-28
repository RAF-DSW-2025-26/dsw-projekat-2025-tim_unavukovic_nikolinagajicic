package raf.graffito.dsw.commands;

import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.model.ImageElement;

public class RotateElementCommand implements Command {
    private Slide slide;
    private ImageElement element;
    private double angle;
    private double previousAngle;

    public RotateElementCommand(Slide slide, ImageElement element, double angle) {
        this.slide = slide;
        this.element = element;
        this.angle = angle;
    }

    @Override
    public void execute() {
        previousAngle = element.getRotationAngle();
        element.setRotationAngle(previousAngle + angle);
        slide.notifyAllSubscribers(slide.getDiagramElements());
        System.out.println("Akcija izvršena: Element rotiran za " + Math.toDegrees(angle) + " stepeni");
    }

    @Override
    public void undo() {
        element.setRotationAngle(previousAngle);
        slide.notifyAllSubscribers(slide.getDiagramElements());
        System.out.println("Akcija poništena: Element vraćen na rotaciju " + Math.toDegrees(previousAngle) + " stepeni");
    }
}

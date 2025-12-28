package raf.graffito.dsw.commands;

import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.model.ImageElement;

import java.awt.*;

public class PasteElementCommand implements Command {
    private Slide slide;
    private ImageElement originalElement;
    private ImageElement copiedElement;

    public PasteElementCommand(Slide slide, ImageElement elementToCopy) {
        this.slide = slide;
        this.originalElement = elementToCopy;

        // Kreiranje kopije sa offsetom
        Point newLocation = new Point(
                elementToCopy.getLokacija().x + 20,
                elementToCopy.getLokacija().y + 20
        );

        // Kreiramo novi ImageElement
        this.copiedElement = new ImageElement(
                elementToCopy.getImage(),
                newLocation
        );

        // Kopiraj dimenziju i rotaciju
        copiedElement.setDimenzija(new Dimension(elementToCopy.getDimenzija()));
        copiedElement.setRotationAngle(elementToCopy.getRotationAngle());
    }

    @Override
    public void execute() {
        slide.addElementDirect(copiedElement);
        System.out.println("Akcija izvršena: Element paste-ovan");
    }

    @Override
    public void undo() {
        slide.removeElementDirect(copiedElement);
        System.out.println("Akcija poništena: Paste-ovani element uklonjen");
    }

    public ImageElement getCopiedElement() {
        return copiedElement;
    }

}

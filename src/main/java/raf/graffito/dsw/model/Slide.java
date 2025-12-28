package raf.graffito.dsw.model;

import raf.graffito.dsw.commands.CommandManager;
import raf.graffito.dsw.model.elements.model.DiagramElement;
import raf.graffito.dsw.model.elements.model.ImageElement;
import raf.graffito.dsw.observer.Publisher;
import raf.graffito.dsw.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Slide extends  GraffLeaf implements Publisher {
    private List<Subscriber> subscriberList = new ArrayList<>();
    private List<DiagramElement> diagramElements = new ArrayList<>();

    private CommandManager commandManager;

    private LogoModel logo;

    public Slide(String ime, GraffNode parent) {
        super(ime, parent);
        this.logo = new LogoModel(50, 50, 1.5, 15.0);
        this.commandManager = new CommandManager();
    }

    public List<DiagramElement> getDiagramElements() {
        return diagramElements;
    }

    public void setDiagramElements(List<DiagramElement> diagramElements) {
        this.diagramElements = diagramElements;
    }

    public void addElement(ImageElement imageElement) {
        diagramElements.add(imageElement);
        notifyAllSubscribers(diagramElements);
    }

    public void removeElement(ImageElement imageElement) {
        diagramElements.remove(imageElement);
        notifyAllSubscribers(diagramElements);
    }

    public void rotateElement(ImageElement imageElement) {
        double angle = Math.toRadians(90); // [cite: 89]
        imageElement.setRotationAngle(imageElement.getRotationAngle() + angle);
        notifyAllSubscribers(diagramElements);
    }

    public void addElementDirect(ImageElement imageElement) {
        diagramElements.add(imageElement);
        notifyAllSubscribers(diagramElements);
    }

    public void removeElementDirect(ImageElement imageElement) {
        diagramElements.remove(imageElement);
        notifyAllSubscribers(diagramElements);
    }

    public LogoModel getLogo() {
        return logo;
    }

    public void setLogo(LogoModel logo) {
        this.logo = logo;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}

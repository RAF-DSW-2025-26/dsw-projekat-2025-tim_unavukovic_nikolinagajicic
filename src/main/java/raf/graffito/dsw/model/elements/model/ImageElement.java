package raf.graffito.dsw.model.elements.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageElement extends DiagramElement{
    private BufferedImage image;

    public ImageElement(BufferedImage image, Point lokacija) {
        super(lokacija, new Dimension(700, 400));
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}

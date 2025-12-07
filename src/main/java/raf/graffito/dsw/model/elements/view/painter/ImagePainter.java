package raf.graffito.dsw.model.elements.view.painter;


import raf.graffito.dsw.model.elements.model.DiagramElement;
import raf.graffito.dsw.model.elements.model.ImageElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ImagePainter extends PrimordialPainter {

    private ImageElement img;

    public ImagePainter(DiagramElement element) {
        super(element);
        this.img = (ImageElement) element;

        Point p = img.getLokacija();
        Dimension d = img.getDimenzija();

        this.oblik =
                new Rectangle2D.Double(p.x, p.y, d.width, d.height);
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(
                img.getImage(),
                img.getLokacija().x,
                img.getLokacija().y,
                img.getDimenzija().width,
                img.getDimenzija().height,
                null
        );
    }
}
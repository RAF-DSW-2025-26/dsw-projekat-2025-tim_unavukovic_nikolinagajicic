package raf.graffito.dsw.controller;

import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.model.ImageElement;
import raf.graffito.dsw.view.PresentationView;
import raf.graffito.dsw.view.SlideView;

import java.awt.image.BufferedImage;
import java.io.File;

public class SlideController {
    private PresentationView presentationView;
    private Slide slide;

    public SlideController( PresentationView presentationView, Slide slide) {
        this.presentationView = presentationView;
        this.slide = slide;
    }

    public void onAddImagesClicked() {
        File[] files = presentationView.chooseImages(presentationView,true);
        for (File f : files) {
            try {
                BufferedImage img = javax.imageio.ImageIO.read(f);
                if (img == null) continue;
                slide.addElement(new ImageElement(img, new java.awt.Point(0,0)));
                presentationView.addSlideThumbnail(img);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}

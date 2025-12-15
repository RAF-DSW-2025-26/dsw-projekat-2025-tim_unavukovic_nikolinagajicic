package raf.graffito.dsw.view;

import raf.graffito.dsw.controller.SlideController;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.controller.MyMouseListener;
import raf.graffito.dsw.model.elements.model.DiagramElement;
import raf.graffito.dsw.model.elements.model.ImageElement;
import raf.graffito.dsw.model.elements.view.painter.ImagePainter;
import raf.graffito.dsw.observer.Subscriber;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SlideView extends JPanel implements Subscriber {
    private Slide slide;
    private String naslov;
    private ImagePainter painter;
    private MyMouseListener controller;
    private JButton jButton = new JButton("Izaberi slike");
    private SlideController slideController;

    private Dimension preferred = new Dimension(700, 400);

    public SlideView(Slide slide, SlideController slideController) {
        this.slide = slide;
        this.naslov = slide.getIme();
        this.slideController = slideController;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));

//        JLabel title = new JLabel(naslov, SwingConstants.CENTER);
//        title.setFont(title.getFont().deriveFont(Font.BOLD, 50f));
//        title.setForeground(Color.BLACK);
        add(jButton, BorderLayout.NORTH);
        jButton.addActionListener(e->{
            slideController.onAddImagesClicked();
        });



        setMaximumSize(new Dimension(preferred.width, preferred.height));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (DiagramElement el : slide.getDiagramElements()) {
            if (el instanceof ImageElement imgEl) {
                g2.drawImage(
                        imgEl.getImage(),
                        imgEl.getLokacija().x,
                        imgEl.getLokacija().y,
                        imgEl.getDimenzija().width,
                        imgEl.getDimenzija().height,
                        null
                );
            }
        }
    }

    public void setSlidePreferredSize(Dimension d) {
        if (d != null) {
            this.preferred = d;
            setMaximumSize(new Dimension(d.width, d.height));
            revalidate();
            repaint();
        }
    }

    @Override public Dimension getPreferredSize() {
        return preferred;
    }

    @Override
    public void update(Object object) {

        if (object instanceof ImageElement) {

            revalidate();
            repaint();

        }

    }
    public void setSlide(Slide slide) {
        this.slide = slide;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public Slide getSlide() {
        return slide;
    }

    public String getNaslov() {
        return naslov;
    }
}

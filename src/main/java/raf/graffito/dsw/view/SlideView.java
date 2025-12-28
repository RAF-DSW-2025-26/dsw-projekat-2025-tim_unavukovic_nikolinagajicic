package raf.graffito.dsw.view;

import raf.graffito.dsw.controller.LogoListener;
import raf.graffito.dsw.controller.SlideController;
import raf.graffito.dsw.model.KrunaModel;
import raf.graffito.dsw.model.LogoModel;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.controller.MyMouseListener;
import raf.graffito.dsw.model.elements.model.DiagramElement;
import raf.graffito.dsw.model.elements.model.ImageElement;
import raf.graffito.dsw.model.elements.view.painter.ImagePainter;
import raf.graffito.dsw.observer.Subscriber;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class SlideView extends JPanel implements Subscriber{
    private Slide slide;
    private String naslov;
    private ImagePainter painter;
    private MyMouseListener controller;
    private JButton jButton = new JButton("Izaberi slike");
    private SlideController slideController;
    private AffineTransform currentTransform = new AffineTransform();


    private LogoListener logoController;
    private LogoPainter logoPainter;

    private LogoListener currentLogoController;

    private Dimension preferred = new Dimension(700, 400);

    private final KrunaModel logoModel = new KrunaModel(40, 40, 1.0, 0.0);
    private final KrunaRenderer logoRenderer = new KrunaRenderer();

    public SlideView(Slide slide, SlideController slideController) {
        this.slide = slide;
        this.naslov = slide.getIme();
        this.slideController = slideController;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));

        add(jButton, BorderLayout.NORTH);
        jButton.addActionListener(slideController);

        setMaximumSize(new Dimension(preferred.width, preferred.height));

        setupLogo();
    }

    private void setupLogo() {
        if (slide.getLogo() != null) {
            logoPainter = new LogoPainter(slide.getLogo());
            logoController = new LogoListener(slide.getLogo(), this);

            addMouseListener(logoController);
            addMouseMotionListener(logoController);

            slide.getLogo().addSubscriber(this);
        }
    }

    private void cleanupLogo() {
        if (logoController != null) {
            removeMouseListener(logoController);
            removeMouseMotionListener(logoController);
        }
        if (slide.getLogo() != null) {
            slide.getLogo().removeSubscriber(this);
        }
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

        if (slide.getLogo() != null) {
            LogoPainter logoPainter = new LogoPainter(slide.getLogo());
            logoPainter.paint(g2);
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
        revalidate();
        repaint();
    }

    public void setSlide(Slide slide) {
        if (currentLogoController != null) {
            this.removeMouseListener(currentLogoController);
            this.removeMouseMotionListener(currentLogoController);
        }

        this.slide = slide;
        this.setName(slide.getIme());

        if (slide.getLogo() != null) {
            currentLogoController = new LogoListener(slide.getLogo(), this);
            this.addMouseListener(currentLogoController);
            this.addMouseMotionListener(currentLogoController);
            System.out.println("Logo kontroler dodat za slajd: " + slide.getIme());
        }

        this.repaint();
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
package raf.graffito.dsw.view;

import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.controller.MyMouseListener;
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

    private Dimension preferred = new Dimension(700, 400);

    public SlideView(Slide slide) {
        this.slide = slide;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));

        JLabel title = new JLabel(naslov, SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        title.setForeground(Color.WHITE);

//        JPanel header = new JPanel(new BorderLayout());
//        header.setOpaque(false);
//        header.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
//        header.add(title, BorderLayout.CENTER);

//        add(header, BorderLayout.NORTH);

//        JPanel jPanel = new JPanel();
//        jPanel.setBackground(Color.BLACK);
//        add(jPanel, BorderLayout.CENTER);

        BufferedImage img;

        try {
            img = ImageIO.read(new File("images/cvet.jpeg"));
        } catch (IOException e) {
            throw new RuntimeException("Ne mogu da učitam sliku", e);
        }


        ImageElement el = new ImageElement(img, new Point(0, 0));
        painter = new ImagePainter(el);
        controller = new MyMouseListener(painter, this);
        addMouseListener(controller);
        addMouseMotionListener(controller);

        setMaximumSize(new Dimension(preferred.width, preferred.height));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        painter.paint((Graphics2D) g);
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

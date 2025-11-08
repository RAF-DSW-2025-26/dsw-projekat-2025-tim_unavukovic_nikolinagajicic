package raf.graffito.dsw.view;

import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import java.awt.*;

public class SlideView extends JPanel implements Subscriber {
    private Slide slide;
    private String naslov;

    private Dimension preferred = new Dimension(400, 400);

    public SlideView(Slide slide) {
        this.slide = slide;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));

        JLabel title = new JLabel(naslov, SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        title.setForeground(Color.BLACK);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        header.add(title, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.CENTER);

        setMaximumSize(new Dimension(preferred.width, preferred.height));

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

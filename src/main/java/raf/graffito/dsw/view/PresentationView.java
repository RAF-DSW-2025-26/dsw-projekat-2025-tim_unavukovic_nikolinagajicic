package raf.graffito.dsw.view;

import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PresentationView extends JPanel implements Subscriber {
    private Presentation presentation;

    private String naslov;
    private String autor;
    private List<SlideView> slajdovi = new ArrayList<>();

    private JScrollPane scrollPane;
    private JPanel content;

    public PresentationView(Presentation presentation) {
        super(new BorderLayout());
        this.presentation = presentation;

        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        scrollPane = new JScrollPane(content,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }
    public void addSlide(SlideView slide) {
        slajdovi.add(slide);
        slide.setOpaque(true);
        slide.setBackground(Color.WHITE);
        // okvir + donji razmak 10px
        Border ivica  = BorderFactory.createLineBorder(new Color(200,200,200), 1);
        Border razmak = BorderFactory.createEmptyBorder(0, 0, 10, 0);
        slide.setBorder(BorderFactory.createCompoundBorder(razmak, ivica));
        // držimo ga centriranog kada je uži od viewporta
        slide.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(slide);
        revalidate();
        repaint();
    }
    public void clearSlides() {
        slajdovi.clear();
        content.removeAll();
        revalidate();
        repaint();
    }

    public void setSlides(List<SlideView> slides) {
        clearSlides();
        for (SlideView s : slides) addSlide(s);
    }

    public Presentation getPresentation() {
        return presentation;
    }

    @Override
    public void update(Object object) {

    }

    public String getNaslov() {
        return naslov;
    }

    public String getAutor() {
        return autor;
    }

    public List<SlideView> getSlajdovi() {
        return slajdovi;
    }
}

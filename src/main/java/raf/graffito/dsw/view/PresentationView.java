package raf.graffito.dsw.view;

import raf.graffito.dsw.controller.ImageListener;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.elements.model.ImageElement;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PresentationView extends JPanel implements Subscriber {
    private Presentation presentation;

    private List<SlideView> slajdovi = new ArrayList<>();

    private JScrollPane scrollPane;
    private JScrollPane thumbScrollPane;
    private JPanel content;
    private JPanel contentDesni;
    private JLabel naslov = new JLabel();
    private JTabbedPane tabbedPane;

    private JPanel thumbsPanel;
    private List<JLabel> thumbLabels = new ArrayList<>();
    private static final int THUMB_H = 200;

    public PresentationView(Presentation presentation, JTabbedPane tabbedPane) {
        super(new BorderLayout());
        this.presentation = presentation;
        this.tabbedPane = tabbedPane;

        naslov.setText("Naslov: " + presentation.getIme());
        add(naslov, BorderLayout.NORTH);

        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(content,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        thumbsPanel = new JPanel();
        thumbsPanel.setLayout(new BoxLayout(thumbsPanel, BoxLayout.Y_AXIS));
        thumbsPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        thumbScrollPane = new JScrollPane(
                thumbsPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        thumbScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        thumbScrollPane.getViewport().addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override public void componentResized(java.awt.event.ComponentEvent e) {
                rescaleAllThumbnails();
            }
        });


        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane,thumbScrollPane);
        add(split,BorderLayout.CENTER);
        split.setDividerLocation(800);
        split.setOneTouchExpandable(true);

    }

    private void rescaleAllThumbnails() {
        for (JLabel lbl : thumbLabels) {
            scaleLabelToViewportWidth(lbl);
        }
        thumbsPanel.revalidate();
        thumbsPanel.repaint();
    }

    private void scaleLabelToViewportWidth(JLabel lbl) {
        Object o = lbl.getClientProperty("orig");
        if (!(o instanceof java.awt.image.BufferedImage orig)) return;

        int viewportW = thumbScrollPane.getViewport().getWidth();
        if (viewportW <= 0) return;

        int targetW = viewportW - 16;
        if (targetW < 50) targetW = 50;

        double sx = targetW / (double) orig.getWidth();
        double sy = THUMB_H / (double) orig.getHeight();
        double s = Math.min(sx, sy);

        int dw = (int) Math.round(orig.getWidth() * s);
        int dh = (int) Math.round(orig.getHeight() * s);

        java.awt.Image scaled = orig.getScaledInstance(dw, dh, java.awt.Image.SCALE_SMOOTH);
        lbl.setIcon(new ImageIcon(scaled));
    }

    public void addSlideThumbnail(BufferedImage img) {
        JLabel lbl = new JLabel();
        lbl.setOpaque(true);
        lbl.setBackground(java.awt.Color.WHITE);
        lbl.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        lbl.setBorder(BorderFactory.createLineBorder(new java.awt.Color(230,230,230)));

        lbl.setMaximumSize(new Dimension(Integer.MAX_VALUE, THUMB_H));
        lbl.setPreferredSize(new Dimension(10, THUMB_H));
        lbl.setMinimumSize(new Dimension(10, THUMB_H));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setVerticalAlignment(SwingConstants.CENTER);

        lbl.putClientProperty("orig", img);

        thumbsPanel.add(lbl);
        thumbsPanel.add(Box.createVerticalStrut(10));
        thumbLabels.add(lbl);

        scaleLabelToViewportWidth(lbl);

        thumbsPanel.revalidate();
        thumbsPanel.repaint();

        for(SlideView slideView : slajdovi){
            ImageElement imageElement = new ImageElement(img, new java.awt.Point(0,0));

            ImageListener imageListener = new ImageListener(imageElement, slideView);
            lbl.addMouseListener(imageListener);

        }

    }

    public void addSlide(SlideView slide) {
        slajdovi.add(slide);
        Border ivica  = BorderFactory.createLineBorder(new Color(200,200,200), 1);
        Border razmak = BorderFactory.createEmptyBorder(0, 0, 10, 0);

        slide.setBorder(BorderFactory.createCompoundBorder(razmak, ivica));
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
        if(object instanceof Presentation) {
            presentation = (Presentation) object;
            naslov.setText("Naslov: " + presentation.getIme());

            for(int i = 0; i< tabbedPane.getTabCount(); i++){
                Component c = tabbedPane.getComponentAt(i);
                if(c instanceof PresentationView){
                    if(c.equals(this)){
                        tabbedPane.setTitleAt(i, presentation.getIme());
                    }
                }
            }

        }

        revalidate();
        repaint();
    }

    public static File[] chooseImages(Component parent, boolean multiSelect) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(multiSelect ? "Select images" : "Select image");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(multiSelect);

        chooser.setAcceptAllFileFilterUsed(true);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter(
                "Images (*.png, *.jpg, *.jpeg, *.gif, *.bmp, *.webp)",
                "png", "jpg", "jpeg", "gif", "bmp", "webp"
        ));

        int res = chooser.showOpenDialog(parent);
        if (res != JFileChooser.APPROVE_OPTION) return new File[0];

        if (multiSelect) return chooser.getSelectedFiles();

        File one = chooser.getSelectedFile();
        return one != null ? new File[]{ one } : new File[0];
    }

    public List<SlideView> getSlajdovi() {
        return slajdovi;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}

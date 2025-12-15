package raf.graffito.dsw.view;

import raf.graffito.dsw.controller.SlideController;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Promena;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.decorator.ColorDecorator;
import raf.graffito.dsw.model.decorator.NodeDecorator;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ProjectView extends JPanel implements Subscriber {
    private Project project;
    private JTabbedPane tabbedPane;
    private JLabel autor = new JLabel();

    public ProjectView(Project project) {
        this.project = project;

        setLayout(new BorderLayout());

        autor.setText("Autor: " + project.getAuthor());
        add(autor, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        add(tabbedPane, BorderLayout.CENTER);
        for(NodeDecorator nodeDecorator : ApplicationFramework.getInstance().getDecorators()) {
            if(nodeDecorator instanceof ColorDecorator) {
                if(nodeDecorator.getComposite().equals(project)){
                    tabbedPane.setBackground(((ColorDecorator) nodeDecorator).getColor());
                }
            }
        }

    }

    @Override
    public void update(Object object) {
        if(object instanceof Project) {
            this.project = (Project) object;
            autor.setText("Autor: " + project.getAuthor());


            for(int i = 0; i<MainFrame.getInstance().getTabbedPane().getTabCount(); i++){
                Component c = MainFrame.getInstance().getTabbedPane().getComponentAt(i);
                if(c instanceof ProjectView){
                    if(c.equals(this)){
                        MainFrame.getInstance().getTabbedPane().setTitleAt(i, project.getTitle());
                    }
                }
            }

            tabbedPane.removeAll();
            for(Presentation presentation : project.getListaPrezentacija()){
                presentation.removeAllSubscribers();
            }

            for(Presentation presentation : project.getListaPrezentacija()){
                PresentationView presentationView = new PresentationView(presentation, tabbedPane);
                presentation.addSubscriber(presentationView);

                for(Slide slide : presentation.getListaSlajdova()){
                    SlideController slideController = new SlideController(presentationView, slide);
                    SlideView slideView = new SlideView(slide, slideController);
                    slide.addSubscriber(slideView);
                    presentationView.addSlide(slideView);
                }
                tabbedPane.addTab(presentation.getIme(), null, presentationView, "Osnovne informacije");

            }



        }

        revalidate();
        repaint();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProjectView that)) return false;
        return Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(project);
    }
}

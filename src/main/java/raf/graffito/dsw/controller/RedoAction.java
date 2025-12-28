package raf.graffito.dsw.controller;

import raf.graffito.dsw.actions.AbstactGraffAction;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.view.PresentationView;
import raf.graffito.dsw.view.ProjectView;
import raf.graffito.dsw.view.SlideView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstactGraffAction {
    public RedoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/redo.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SlideView currentSlideView = getCurrentSlideView();
        if (currentSlideView != null && currentSlideView.getSlide() != null) {
            currentSlideView.getSlide().getCommandManager().redo();
        }
    }

    private SlideView getCurrentSlideView() {
        Component selectedTab = MainFrame.getInstance().getTabbedPane().getSelectedComponent();
        if (selectedTab instanceof ProjectView) {
            ProjectView projectView = (ProjectView) selectedTab;
            Component presentationTab = projectView.getTabbedPane().getSelectedComponent();

            if (presentationTab instanceof PresentationView) {
                PresentationView presentationView = (PresentationView) presentationTab;


                for (SlideView slideView : presentationView.getSlajdovi()) {
                    if (slideView.isVisible()) {
                        return slideView;
                    }
                }
            }
        }
        return null;
    }
}
package raf.graffito.dsw.controller;

import raf.graffito.dsw.actions.AbstactGraffAction;
import raf.graffito.dsw.commands.PasteElementCommand;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.model.elements.model.ImageElement;
import raf.graffito.dsw.view.PresentationView;
import raf.graffito.dsw.view.ProjectView;
import raf.graffito.dsw.view.SlideView;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PasteAction extends AbstactGraffAction {
    private CopyAction copyAction;

    public PasteAction(CopyAction copyAction) {
        this.copyAction = copyAction;
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/paste.png"));
        putValue(NAME, "Paste");
        putValue(SHORT_DESCRIPTION, "Paste");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ImageElement clipboard = copyAction.getClipboard();
        if (clipboard == null) {
            System.out.println("Clipboard je prazan - nema šta da se paste-uje");
            return;
        }

        SlideView currentSlideView = getCurrentSlideView();
        if (currentSlideView != null && currentSlideView.getSlide() != null) {
            PasteElementCommand command = new PasteElementCommand(
                    currentSlideView.getSlide(),
                    clipboard
            );
            currentSlideView.getSlide().getCommandManager().executeCommand(command);
            System.out.println("Paste akcija - element paste-ovan");
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

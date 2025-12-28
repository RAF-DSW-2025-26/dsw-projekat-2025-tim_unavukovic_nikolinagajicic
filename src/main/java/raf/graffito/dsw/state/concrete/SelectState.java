package raf.graffito.dsw.state.concrete;

import raf.graffito.dsw.model.elements.model.ImageElement;
import raf.graffito.dsw.state.State;
import raf.graffito.dsw.view.SlideView;

public class SelectState implements State {

    public void performOperation(Object object) {
    }


    public void performOperation(Object object, Object object2) {
        if (object instanceof SlideView && object2 instanceof ImageElement) {
            SlideView slideView = (SlideView) object;
            ImageElement element = (ImageElement) object2;

            // Toggle selection
            boolean currentlySelected = element.isSelected();
            element.setSelected(!currentlySelected);

            System.out.println("Element " + (element.isSelected() ? "selektovan" : "deselektovan"));

            // Refresh view
            slideView.repaint();
        }
    }
}

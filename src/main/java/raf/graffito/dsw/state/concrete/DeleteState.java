package raf.graffito.dsw.state.concrete;

import raf.graffito.dsw.commands.DeleteElementCommand;
import raf.graffito.dsw.model.elements.model.ImageElement;
import raf.graffito.dsw.state.State;
import raf.graffito.dsw.view.SlideView;

public class DeleteState implements State {
    @Override
    public void performOperation(Object object) {

    }

  /**  @Override
    public void performOperation(Object object, Object object2) {
        if(object instanceof SlideView && object2 instanceof ImageElement){

            ((SlideView) object).getSlide().removeElement((ImageElement) object2);

        }
    }*/

  @Override
  public void performOperation(Object object, Object object2) {
      if(object instanceof SlideView && object2 instanceof ImageElement){
          SlideView slideView = (SlideView) object;
          ImageElement element = (ImageElement) object2;

          DeleteElementCommand command = new DeleteElementCommand(
                  slideView.getSlide(),
                  element
          );
          slideView.getSlide().getCommandManager().executeCommand(command);
      }
  }
}

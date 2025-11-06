package raf.graffito.dsw.actions;

import raf.graffito.dsw.controller.AboutUsAction;
import raf.graffito.dsw.controller.AddNodeAction;
import raf.graffito.dsw.controller.ExitAction;

public class ActionManager {
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private AddNodeAction addNodeAction;
    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.addNodeAction = new AddNodeAction();
    }

    public AddNodeAction getAddNodeAction() {
        return addNodeAction;
    }

    public void setAddNodeAction(AddNodeAction addNodeAction) {
        this.addNodeAction = addNodeAction;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public void setExitAction(ExitAction exitAction) {
        this.exitAction = exitAction;
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }

    public void setAboutUsAction(AboutUsAction aboutUsAction) {
        this.aboutUsAction = aboutUsAction;
    }
}

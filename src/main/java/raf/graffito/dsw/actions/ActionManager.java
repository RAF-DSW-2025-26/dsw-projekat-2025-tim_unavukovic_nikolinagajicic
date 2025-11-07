package raf.graffito.dsw.actions;

import raf.graffito.dsw.controller.*;

public class ActionManager {
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private AddNodeAction addNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private RenameAutorAction renameAutorAction;
    private RenameNaslovAction renameNaslovAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.addNodeAction = new AddNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.renameAutorAction = new RenameAutorAction();
        this.renameNaslovAction = new RenameNaslovAction();
    }

    public RenameAutorAction getRenameAutorAction() {
        return renameAutorAction;
    }

    public void setRenameAutorAction(RenameAutorAction renameAutorAction) {
        this.renameAutorAction = renameAutorAction;
    }

    public RenameNaslovAction getRenameNaslovAction() {
        return renameNaslovAction;
    }

    public void setRenameNaslovAction(RenameNaslovAction renameNaslovAction) {
        this.renameNaslovAction = renameNaslovAction;
    }

    public DeleteNodeAction getDeleteNodeAction() {
        return deleteNodeAction;
    }

    public void setDeleteNodeAction(DeleteNodeAction deleteNodeAction) {
        this.deleteNodeAction = deleteNodeAction;
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

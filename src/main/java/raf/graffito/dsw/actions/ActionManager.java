package raf.graffito.dsw.actions;

import raf.graffito.dsw.controller.*;

public class ActionManager {
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private AddNodeAction addNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private RenameAutorAction renameAutorAction;
    private RenameNaslovAction renameNaslovAction;

    private AddAction addAction;
    private DeleteAction deleteAction;
    private ResizeAction resizeAction;
    private RotateAction rotateAction;
    private ZoomAction zoomAction;
    private SelectAction selectAction;

    private UndoAction undoAction;
    private RedoAction redoAction;

    private CopyAction copyAction;
    private PasteAction pasteAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.addNodeAction = new AddNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.renameAutorAction = new RenameAutorAction();
        this.renameNaslovAction = new RenameNaslovAction();

        this.addAction = new AddAction();
        this.deleteAction = new DeleteAction();
        this.resizeAction = new ResizeAction();
        this.rotateAction = new RotateAction();
        this.zoomAction = new ZoomAction();
        this.selectAction = new SelectAction();

        this.undoAction = new UndoAction();
        this.redoAction = new RedoAction();

        this.copyAction = new CopyAction();
        this.pasteAction = new PasteAction(copyAction);
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

    public AddAction getAddAction() {
        return addAction;
    }

    public void setAddAction(AddAction addAction) {
        this.addAction = addAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(DeleteAction deleteAction) {
        this.deleteAction = deleteAction;
    }

    public ResizeAction getResizeAction() {
        return resizeAction;
    }

    public void setResizeAction(ResizeAction resizeAction) {
        this.resizeAction = resizeAction;
    }

    public RotateAction getRotateAction() {
        return rotateAction;
    }

    public void setRotateAction(RotateAction rotateAction) {
        this.rotateAction = rotateAction;
    }

    public ZoomAction getZoomAction() {
        return zoomAction;
    }
    public SelectAction getSelectAction() { return selectAction; }

    public void setZoomAction(ZoomAction zoomAction) {
        this.zoomAction = zoomAction;
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

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public void setUndoAction(UndoAction undoAction) {
        this.undoAction = undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public void setRedoAction(RedoAction redoAction) {
        this.redoAction = redoAction;
    }
    public CopyAction getCopyAction() { return copyAction; }
    public PasteAction getPasteAction() { return pasteAction; }
    public void setSelectAction(SelectAction selectAction) {
        this.selectAction = selectAction;
    }
}

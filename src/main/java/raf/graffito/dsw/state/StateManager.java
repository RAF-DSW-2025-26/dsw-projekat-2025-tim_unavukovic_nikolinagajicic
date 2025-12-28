package raf.graffito.dsw.state;

import raf.graffito.dsw.state.concrete.*;

public class StateManager {

    private State currentState;
    private AddState addState;
    private DeleteState deleteState;
    private ResizeState resizeState;
    private RotateState rotateState;
    private ZoomState zoomState;


    public StateManager() {
        initStates();
    }

    private void initStates() {
        addState = new AddState();
        deleteState = new DeleteState();
        resizeState = new ResizeState();
        rotateState = new RotateState();
        zoomState = new ZoomState();
        currentState = addState;
    }

    public State getCurrent(){
        return currentState;
    }

    public void setAddState(){
        currentState = addState;
    }

    public void setDeleteState(){
        currentState = deleteState;
    }

    public void setResizeState(){
        currentState = resizeState;
    }

    public void setRotateState(){
        currentState = rotateState;
    }

    public void setZoomState(){
        currentState = zoomState;
    }

}

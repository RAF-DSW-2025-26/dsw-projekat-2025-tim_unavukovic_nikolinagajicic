package raf.graffito.dsw.state;

public interface State {
    void performOperation(Object object);
    void performOperation(Object object, Object object2);
}

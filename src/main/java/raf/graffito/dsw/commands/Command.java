package raf.graffito.dsw.commands;

public interface Command {
    void execute();
    void undo();
}

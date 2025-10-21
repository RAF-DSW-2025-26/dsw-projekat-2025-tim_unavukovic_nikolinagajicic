package raf.graffito.dsw.core;
import raf.graffito.dsw.gui.swing.MainFrame;

public class ApplicationFramework {
    public static ApplicationFramework instance= null;
    // Buduća polja za model celog projekta

    private ApplicationFramework(){
        initialize();
    }

    public void initialize(){
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }

    public static ApplicationFramework getInstance() {
        if (instance == null) {
            instance = new ApplicationFramework();
        }
        return instance;
    }
}

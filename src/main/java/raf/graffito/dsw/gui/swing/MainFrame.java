package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.actions.ActionManager;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.GraffTree;
import raf.graffito.dsw.gui.swing.tree.GraffTreeImplementation;
import raf.graffito.dsw.model.KrunaModel;
import raf.graffito.dsw.observer.Poruka;
import raf.graffito.dsw.observer.Subscriber;
import raf.graffito.dsw.state.StateManager;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Subscriber {
    public static MainFrame instance=null;
    private ActionManager actionManager;
    private GraffTree graffTree;
    private JTabbedPane tabbedPane;

    private StateManager stateManager;

    private MainFrame() {
        initialize();
    }

    private void initialize() {
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graffito");

        actionManager = new ActionManager();
        stateManager = new StateManager();

        MyMenuBar menu = new MyMenuBar(actionManager);
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar(actionManager);

        // --- INTEGRACIJA LOGOA ---
        // 1. Kreiramo Model (pozicija 100,100, skala 1.0, rotacija 0)
        KrunaModel logoModel = new KrunaModel(100, 100, 1.0, 0.0);

        // 2. Kreiramo View i prosleđujemo mu model


        // (Opciono) Možete ga dodati na ToolBar ili negde drugde
        // Ovde ga dodajemo na ToolBar kao primer da se vidi odmah
        toolBar.addSeparator();


        add(toolBar, BorderLayout.NORTH);

        StateBar stateBar = new StateBar(actionManager);
        add(stateBar, BorderLayout.WEST);


        graffTree = new GraffTreeImplementation();

        JTree projectExplorer = graffTree.generateTree(ApplicationFramework.getInstance().getGraffRepository().getWorkSpace());


        JScrollPane scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,tabbedPane);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    @Override
    public void update(Object object) {
        if (object instanceof Poruka){
            JOptionPane.showMessageDialog(null,((Poruka)object).toString(), String.valueOf(((Poruka) object).getTipPoruke()),JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public GraffTree getGraffTree() {
        return graffTree;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void startAddState(){
        this.stateManager.setAddState();
    }

    public void startDeleteState(){
        this.stateManager.setDeleteState();
    }

    public void startResizeState(){
        this.stateManager.setResizeState();
    }

    public void startRotateState(){
        this.stateManager.setRotateState();
    }

    public void startZoomState(){
        this.stateManager.setZoomState();
    }

    public StateManager getStateManager() {
        return stateManager;
    }
}

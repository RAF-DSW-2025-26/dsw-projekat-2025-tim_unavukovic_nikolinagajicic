package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.actions.ActionManager;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.GraffTree;
import raf.graffito.dsw.gui.swing.tree.GraffTreeImplementation;
import raf.graffito.dsw.observer.Poruka;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Subscriber {
    public static MainFrame instance=null;
    private ActionManager actionManager;
    private GraffTree graffTree;
    private JTabbedPane tabbedPane;


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
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graffito");

        actionManager = new ActionManager();

        MyMenuBar menu = new MyMenuBar(actionManager);
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar(actionManager);
        add(toolBar, BorderLayout.NORTH);

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
}

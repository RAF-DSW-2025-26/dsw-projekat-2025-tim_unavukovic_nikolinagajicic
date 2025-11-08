package raf.graffito.dsw.core;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.repository.GraffRepositoryImpl;
import raf.graffito.dsw.model.decorator.NodeDecorator;
import raf.graffito.dsw.observer.MessageGenerator;
import raf.graffito.dsw.observer.simpleFactory.ConsoleLogger;
import raf.graffito.dsw.observer.simpleFactory.FileLogger;
import raf.graffito.dsw.observer.simpleFactory.LoggerFactory;
import raf.graffito.dsw.repository.GraffRepository;

import java.util.ArrayList;
import java.util.List;

public class ApplicationFramework {

    private MessageGenerator messageGenerator;
    private LoggerFactory loggerFactory;
    private ConsoleLogger consoleLogger;
    private FileLogger fileLogger;
    protected GraffRepository graffRepository;
    public List<NodeDecorator> decorators = new ArrayList<>();

    public static ApplicationFramework instance= null;


    private ApplicationFramework() {
        this.graffRepository = new GraffRepositoryImpl();
    }

    public void initialize(){
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
        messageGenerator = new MessageGenerator();
        messageGenerator.addSubscriber(mainFrame);
        loggerFactory = new LoggerFactory();
        fileLogger = (FileLogger) loggerFactory.createLogger("file");
        consoleLogger = (ConsoleLogger) loggerFactory.createLogger("console");
        messageGenerator.addSubscriber(fileLogger);
        messageGenerator.addSubscriber(consoleLogger);
    }

    public static ApplicationFramework getInstance() {
        if (instance == null) {
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public GraffRepository getGraffRepository() {
        return graffRepository;
    }

    public void setGraffRepository(GraffRepository graffRepository) {
        this.graffRepository = graffRepository;
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public List<NodeDecorator> getDecorators() {
        return decorators;
    }
}

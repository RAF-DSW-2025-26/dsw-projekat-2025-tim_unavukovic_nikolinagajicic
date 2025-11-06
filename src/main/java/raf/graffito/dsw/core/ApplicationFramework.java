package raf.graffito.dsw.core;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.repository.GraffRepositoryImpl;
import raf.graffito.dsw.observer.MessageGenerator;
import raf.graffito.dsw.observer.Poruka;
import raf.graffito.dsw.observer.TipPoruke;
import raf.graffito.dsw.observer.factory.ConsoleLogger;
import raf.graffito.dsw.observer.factory.FileLogger;
import raf.graffito.dsw.observer.factory.LoggerFactory;
import raf.graffito.dsw.repository.GraffRepository;

public class ApplicationFramework {

    private MessageGenerator messageGenerator;
    private LoggerFactory loggerFactory;
    private ConsoleLogger consoleLogger;
    private FileLogger fileLogger;
    protected GraffRepository graffRepository;

    public static ApplicationFramework instance= null;
    // Buduća polja za model celog projekta

    private ApplicationFramework() {
//        messageGenerator.notifyAllSubscribers(new Poruka(TipPoruke.OBAVESTENJE,"proba"));
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
        /// da li je na dobrom mestu dodat messageGenerator i loggeri
        /// i kako napravitin pretragu po imenu
        ///  kako da stavimo u resource folder u fileLoggeru
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
}

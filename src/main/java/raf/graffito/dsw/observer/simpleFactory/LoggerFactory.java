package raf.graffito.dsw.observer.simpleFactory;

public class LoggerFactory {

    public Logger createLogger(String name) {
        if( name.equals("file")){
            return new FileLogger();
        }else if(name.equals("console")){
            return new ConsoleLogger();
        }
        return null;
    }
}

package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerConf {

    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

//    public void setLogger(Logger logger) {
//        this.logger = logger;
//    }

    public LoggerConf(String className) throws IOException {
        this.logger = Logger.getLogger(className);
        try {
            LogManager.getLogManager().readConfiguration(
                   className.getClass().getResourceAsStream("/logging.properties"));
        }catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        FileHandler fh = new FileHandler("log.txt");
        // Send logger output to our FileHandler.
        logger.addHandler(fh);
        // Request that every detail gets logged.
//        logger.setLevel(Level.ALL);

    }
}

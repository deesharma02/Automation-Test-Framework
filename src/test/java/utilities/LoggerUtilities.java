package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtilities {
    private static Logger logger;

    private LoggerUtilities(){

    }

    public static Logger getLogger(Class<?> clazz){
        if(logger == null){
            logger = LogManager.getLogger(clazz);
        }
        return logger;
    }
}

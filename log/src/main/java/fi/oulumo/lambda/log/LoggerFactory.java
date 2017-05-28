package fi.oulumo.lambda.log;

/**
 * Utility class to create loggers in the lambda request-handler classes.
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class LoggerFactory {
    private static ILoggerCreator loggerCreator = new HandlerContextLoggerCreator();

    private LoggerFactory() {
        // Utility class, no instances are allowed.
    }

    public static void setLoggerCreator(ILoggerCreator loggerCreator) {
        LoggerFactory.loggerCreator = loggerCreator;
    }

    public static ILogger createLogger(String name) {
        return loggerCreator.createLogger(name);
    }

    public static ILogger createLogger(Class clazz) {
        return loggerCreator.createLogger(clazz.getName());
    }
}

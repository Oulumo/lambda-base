package fi.oulumo.lambda.log;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public interface ILoggerCreator {
    ILogger createLogger(String name);
}

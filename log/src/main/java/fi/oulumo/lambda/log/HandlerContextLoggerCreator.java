package fi.oulumo.lambda.log;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class HandlerContextLoggerCreator implements ILoggerCreator {
    @Override
    public ILogger createLogger(String name) {
        return new HandlerContextLogger(name);
    }
}

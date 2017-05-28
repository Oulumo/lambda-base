package fi.oulumo.lambda.log;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public abstract class AbstractLevelCappedLogger extends AbstractLogger {
    public AbstractLevelCappedLogger(String loggerName) {
        super(loggerName);
    }


    @Override
    protected void doLog(LogLevel level, String loggerName, Throwable t, String message, Object... arguments) {
        if (isLogLevelAllowed(level)) {
            doCappedLog(level, loggerName, t, message, arguments);
        }
    }

    protected abstract boolean isLogLevelAllowed(LogLevel level);

    protected abstract void doCappedLog(LogLevel level, String loggerName, Throwable t, String message, Object[] arguments);
}

package fi.oulumo.lambda.log;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public abstract class AbstractLogger implements ILogger {
    public enum LogLevel {
        All(Integer.MAX_VALUE), Error(1000), Warning(900), Info(800), Debug(700), Trace(600), None(Integer.MIN_VALUE);

        private int level;

        LogLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }

    private String loggerName;

    public AbstractLogger(String loggerName) {
        this.loggerName = loggerName;
    }

    protected abstract void doLog(LogLevel level, String loggerName, Throwable t, String message, Object... arguments);

    @Override
    public void error(String message, Object... arguments) {
        doLog(LogLevel.Error, loggerName, null, message, arguments);
    }

    @Override
    public void error(Throwable t, String message, Object... arguments) {
        doLog(LogLevel.Error, loggerName, t, message, arguments);
    }

    @Override
    public void warn(String message, Object... arguments) {
        doLog(LogLevel.Warning, loggerName, null, message, arguments);
    }

    @Override
    public void warn(Throwable t, String message, Object... arguments) {
        doLog(LogLevel.Warning, loggerName, t, message, arguments);
    }

    @Override
    public void info(String message, Object... arguments) {
        doLog(LogLevel.Info, loggerName, null, message, arguments);
    }

    @Override
    public void info(Throwable t, String message, Object... arguments) {
        doLog(LogLevel.Info, loggerName, t, message, arguments);
    }

    @Override
    public void debug(String message, Object... arguments) {
        doLog(LogLevel.Debug, loggerName, null, message, arguments);
    }

    @Override
    public void debug(Throwable t, String message, Object... arguments) {
        doLog(LogLevel.Debug, loggerName, t, message, arguments);
    }

    @Override
    public void trace(String message, Object... arguments) {
        doLog(LogLevel.Trace, loggerName, null, message, arguments);
    }

    @Override
    public void trace(Throwable t, String message, Object... arguments) {
        doLog(LogLevel.Trace, loggerName, t, message, arguments);
    }
}

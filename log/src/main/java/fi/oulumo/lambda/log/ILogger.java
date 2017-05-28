package fi.oulumo.lambda.log;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public interface ILogger {
    void error(String message, Object... arguments);

    void error(Throwable t, String message, Object... arguments);

    void warn(String message, Object... arguments);

    void warn(Throwable t, String message, Object... arguments);

    void info(String message, Object... arguments);

    void info(Throwable t, String message, Object... arguments);

    void debug(String message, Object... arguments);

    void debug(Throwable t, String message, Object... arguments);

    void trace(String message, Object... arguments);

    void trace(Throwable t, String message, Object... arguments);
}

package fi.oulumo.lambda.log;

import fi.oulumo.lambda.core.context.HandlerContextHolderUtil;
import fi.oulumo.lambda.core.util.StringUtils;
import fi.oulumo.lambda.log.util.LogUtils;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class HandlerContextLogger extends AbstractLevelCappedLogger {
    private static final String LOG_FORMAT = "%s RequestId: %s [%s] %s";

    public HandlerContextLogger(String loggerName) {
        super(loggerName);
    }

    @Override
    protected boolean isLogLevelAllowed(LogLevel level) {
        if (HandlerContextHolderUtil.isProductionStage()) {
            if (level.getLevel() < LogLevel.Info.getLevel()) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected void doCappedLog(LogLevel level, String loggerName, Throwable t, String message, Object[] arguments) {
        String levelIndicator = level.name().toUpperCase();

        if (!StringUtils.hasText(message)) {
            message = null;
        }
        else if (arguments.length > 0) {
            message = String.format(message, arguments);
        }

        message = LogUtils.combineMessages(message, t);

        String shortLoggerName;

        if (loggerName.contains(".")) {
            shortLoggerName = loggerName.substring(loggerName.lastIndexOf(".") + 1);

            if (!StringUtils.hasText(shortLoggerName)) {
                shortLoggerName = loggerName;
            }
        }
        else {
            shortLoggerName = loggerName;
        }

        if (StringUtils.hasText(message)) {
            HandlerContextHolderUtil.log(String.format(LOG_FORMAT, levelIndicator, HandlerContextHolderUtil.getRequestId(), shortLoggerName, message));
        }
    }
}

package fi.oulumo.lambda.log.util;

import org.apache.commons.io.output.StringBuilderWriter;

import java.io.PrintWriter;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class LogUtils {
    private LogUtils() {
        // Utility class, no instances allowed
    }

    public static String getThrowableMessage(Throwable t) {
        StringBuilder retValue = new StringBuilder();

        writeThrowableToBuilder(t, retValue);

        return retValue.toString();
    }

    public static String combineMessages(String message, Throwable t) {
        if ((message == null) && (t == null)) {
            return null;
        }
        else {
            StringBuilder builder = new StringBuilder();

            if (message != null) {
                builder.append(message);
            }
            if (t != null) {
                if (message != null) {
                    builder.append("\n");
                }

                writeThrowableToBuilder(t, builder);
            }

            return builder.toString();
        }
    }

    private static void writeThrowableToBuilder(Throwable t, StringBuilder builder) {
        StringBuilderWriter stringBuilderWriter = new StringBuilderWriter(builder);
        PrintWriter printWriter = new PrintWriter(stringBuilderWriter);
        t.printStackTrace(printWriter);
        printWriter.flush();
    }
}

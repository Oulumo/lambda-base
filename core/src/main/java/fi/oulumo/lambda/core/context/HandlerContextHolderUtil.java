package fi.oulumo.lambda.core.context;

import fi.oulumo.lambda.core.util.StageUtils;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class HandlerContextHolderUtil {
    private static final String UNKNOWN_REQUEST_ID = "Unknown";

    private HandlerContextHolderUtil() {
        // Utility class, no instances are allowed
    }

    public static void log(String message){
        IHandlerContext context = HandlerContextHolder.get();

        if (context != null) {
            context.getLambdaLogger().log(message);
        }
        else {
            System.out.println(message);
        }
    }

    public static String getRequestId(){
        IHandlerContext context = HandlerContextHolder.get();

        if (context != null) {
            return context.getRequestId();
        }
        else {
            return UNKNOWN_REQUEST_ID;
        }
    }

    public static boolean isProductionStage() {
        IHandlerContext context = HandlerContextHolder.get();

        if (context != null) {
            return context.isProductionStage();
        }
        else {
            return false;
        }
    }

    public static String getStageName() {
        IHandlerContext context = HandlerContextHolder.get();

        if (context != null) {
            return context.getStageName();
        }
        else {
            return StageUtils.getCanonicalStageNameFrom(null);
        }
    }
}

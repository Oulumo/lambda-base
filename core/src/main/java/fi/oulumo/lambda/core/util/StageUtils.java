package fi.oulumo.lambda.core.util;

/**
 * Small utility class to work with deployment stages.
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class StageUtils {
    public static final String DEVELOPMENT_STAGE = "dev";
    public static final String PRODUCTION_STAGE = "prod";

    private StageUtils() {
        // Utility class, no instances are allowed.
    }

    public static boolean isProductionStage(String stage) {
        if (StringUtils.hasText(stage)) {
            String cleanStage = stage.trim().toLowerCase();

            return (PRODUCTION_STAGE.equals(cleanStage));
        }

        return false;
    }

    public static String getCanonicalStageNameFrom(String stage) {
        if (StringUtils.hasText(stage)) {
            return stage.trim().toLowerCase();
        }

        return DEVELOPMENT_STAGE;
    }
}

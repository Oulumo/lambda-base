package fi.oulumo.lambda.dynamodb.util;

import fi.oulumo.lambda.core.util.StageUtils;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class PrefixUtils {
    public static final String S3_BUCKET_NAME_STAGE_DELIMITER = "-";

    private PrefixUtils() {
        // Utility class, no instances are allowed.
    }

    public static String getDynamoDbTablePrefixFrom(String stage) {
        return StageUtils.getCanonicalStageNameFrom(stage).toUpperCase() + "-";
    }
}

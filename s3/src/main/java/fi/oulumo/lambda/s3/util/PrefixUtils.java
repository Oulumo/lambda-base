package fi.oulumo.lambda.s3.util;


import fi.oulumo.lambda.core.util.StageUtils;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class PrefixUtils {
    public static final String S3_BUCKET_NAME_STAGE_DELIMITER = "-";

    private PrefixUtils() {
        // Utility class, no instances are allowed.
    }

    public static String getPrefixedS3BucketName(String stage, String bucketName) {
        StringBuilder retValue = new StringBuilder();
        retValue.append(StageUtils.getCanonicalStageNameFrom(stage));
        if (!bucketName.startsWith(S3_BUCKET_NAME_STAGE_DELIMITER)) {
            retValue.append(S3_BUCKET_NAME_STAGE_DELIMITER);
        }
        retValue.append(bucketName);

        return retValue.toString();
    }
}

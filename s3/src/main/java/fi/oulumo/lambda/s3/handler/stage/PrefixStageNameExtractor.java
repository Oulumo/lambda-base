package fi.oulumo.lambda.s3.handler.stage;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;
import fi.oulumo.lambda.core.util.StageUtils;
import fi.oulumo.lambda.core.util.StringUtils;
import fi.oulumo.lambda.s3.util.PrefixUtils;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class PrefixStageNameExtractor implements IStageNameExtractor {
    @Override
    public String extractStageNameFrom(S3Event input) {
        S3EventNotification.S3EventNotificationRecord record = input.getRecords().get(0);

        String bucketName = record.getS3().getBucket().getName();

        return extractStageNameFromBucketName(bucketName);
    }

    protected static String extractStageNameFromBucketName(String bucketName) {
        if (StringUtils.hasText(bucketName)) {
            if (bucketName.startsWith(PrefixUtils.S3_BUCKET_NAME_STAGE_DELIMITER)) {
                bucketName = bucketName.substring(1);
            }

            if (bucketName.contains(PrefixUtils.S3_BUCKET_NAME_STAGE_DELIMITER)) {
                String stageName = bucketName.substring(0, bucketName.indexOf(PrefixUtils.S3_BUCKET_NAME_STAGE_DELIMITER));

                return StageUtils.getCanonicalStageNameFrom(stageName);
            }
        }

        return StageUtils.getCanonicalStageNameFrom(null);
    }
}

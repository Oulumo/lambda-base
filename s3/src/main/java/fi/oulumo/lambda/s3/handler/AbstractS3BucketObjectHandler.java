package fi.oulumo.lambda.s3.handler;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;
import fi.oulumo.lambda.core.context.IHandlerContextDecorator;
import fi.oulumo.lambda.core.util.StringUtils;
import fi.oulumo.lambda.log.ILogger;
import fi.oulumo.lambda.log.LoggerFactory;
import fi.oulumo.lambda.s3.handler.stage.IStageNameExtractor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public abstract class AbstractS3BucketObjectHandler extends AbstractS3Handler {
    public static final ILogger log = LoggerFactory.createLogger(AbstractS3BucketObjectHandler.class);


    public AbstractS3BucketObjectHandler(IHandlerContextDecorator handlerContextDecorator, IStageNameExtractor stageNameExtractor) {
        super(handlerContextDecorator, stageNameExtractor);
    }

    @Override
    protected boolean doHandleRequest(S3Event input) {
        S3EventNotification.S3EventNotificationRecord record = input.getRecords().get(0);

        String bucketName = record.getS3().getBucket().getName();
        String objectKey = record.getS3().getObject().getKey();

        if (StringUtils.allHasText(bucketName, objectKey)) {
            try {
                objectKey = objectKey.replace('+', '_');
                objectKey = URLDecoder.decode(objectKey, "UTF-8");

                return doHandleRequest(bucketName, objectKey, record.getEventName(), input);
            }
            catch (UnsupportedEncodingException e) {
                log.error(e, "Object name encoding error.");

                return false;
            }
        }
        else {
            log.error("Bucket and/or object name is empty.");

            return false;
        }
    }

    protected abstract boolean doHandleRequest(String bucketName, String objectKey, String eventType, S3Event input);
}

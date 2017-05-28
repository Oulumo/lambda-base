package fi.oulumo.lambda.s3.handler.stage;

import com.amazonaws.services.lambda.runtime.events.S3Event;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public interface IStageNameExtractor {
    String extractStageNameFrom(S3Event inputEvent);
}

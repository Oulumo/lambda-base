package fi.oulumo.lambda.s3;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import fi.oulumo.lambda.s3.handler.AbstractS3Handler;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public abstract class AbstractS3Lambda <L extends AbstractS3Handler> implements RequestHandler<S3Event, String> {
    private L s3handler;

    public AbstractS3Lambda() {
        s3handler = createS3Handler();
    }

    protected abstract L createS3Handler();

    @Override
    public String handleRequest(S3Event input, Context context) {
        return s3handler.handleRequest(input, context);
    }
}

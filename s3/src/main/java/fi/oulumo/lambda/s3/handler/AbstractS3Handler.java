package fi.oulumo.lambda.s3.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import fi.oulumo.lambda.core.buildinfo.BuildInfo;
import fi.oulumo.lambda.core.context.HandlerContextHolder;
import fi.oulumo.lambda.core.context.IHandlerContextDecorator;
import fi.oulumo.lambda.core.util.StageUtils;
import fi.oulumo.lambda.log.ILogger;
import fi.oulumo.lambda.log.LoggerFactory;
import fi.oulumo.lambda.s3.handler.stage.IStageNameExtractor;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public abstract class AbstractS3Handler {
    public static final ILogger log = LoggerFactory.createLogger(AbstractS3Handler.class);

    public static final String RETURN_VALUE_OK = "Ok";
    public static final String RETURN_VALUE_ERROR = "Error";

    private final IHandlerContextDecorator handlerContextDecorator;
    private final IStageNameExtractor stageNameExtractor;

    public AbstractS3Handler(IHandlerContextDecorator handlerContextDecorator, IStageNameExtractor stageNameExtractor) {
        this.handlerContextDecorator = handlerContextDecorator;
        this.stageNameExtractor = stageNameExtractor;
    }

    @SuppressWarnings("Duplicates")
    public String handleRequest(S3Event input, Context context) {
        String stage = StageUtils.getCanonicalStageNameFrom(stageNameExtractor.extractStageNameFrom(input));

        HandlerContext handlerContext = new HandlerContext(context, stage);
        if (handlerContextDecorator != null) {
            handlerContextDecorator.decorateContext(handlerContext);
        }

        try {
            HandlerContextHolder.set(handlerContext);
            log.info(BuildInfo.getInfoString());

            boolean response = doHandleRequest(input);

            log.info("Request handled - response is: %1$s", response ? RETURN_VALUE_OK : RETURN_VALUE_ERROR);
            return response ? RETURN_VALUE_OK : RETURN_VALUE_ERROR;
        }
        finally {
            HandlerContextHolder.remove();
        }
    }

    protected abstract boolean doHandleRequest(S3Event input);

    protected HandlerContext getHandlerContext() {
        return (HandlerContext) HandlerContextHolder.get();
    }
}

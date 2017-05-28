package fi.oulumo.lambda.apigateway.handler;

import com.google.gson.Gson;
import fi.oulumo.lambda.apigateway.exception.LambdaException;
import fi.oulumo.lambda.core.context.HandlerContextHolder;
import fi.oulumo.lambda.log.ILogger;
import fi.oulumo.lambda.log.LoggerFactory;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public abstract class AbstractLambdaRequestHandler<I, O> implements ILambdaRequestHandler<I, O> {
    private static final ILogger log = LoggerFactory.createLogger(AbstractLambdaRequestHandler.class);
    private static final String ERROR_LOG_FORMAT = "Problem in handler: %1$s with error code: %2$s and message: %3$s";

    @Override
    public HandlerResponse<O> handleLambdaRequest(String input, Gson gson) {
        I inputObject = null;

        if ((input != null) && (getInputClass() != null)){
            inputObject = gson.fromJson(input, getInputClass());
        }

        try {
            return doHandleLambdaRequest(inputObject);
        }
        catch (LambdaException e) {
            logLambdaException(e);

            throw e;
        }
    }

    protected abstract HandlerResponse<O> doHandleLambdaRequest(I input);

    protected HandlerContext getHandlerContext() {
        return (HandlerContext) HandlerContextHolder.get();
    }

    @SuppressWarnings("WeakerAccess")
    protected void logLambdaException(LambdaException e) {
        log.error(
                ERROR_LOG_FORMAT,
                this.getClass().getSimpleName(),
                e.getStatusCode().getCode(),
                e.getMessage()
        );
    }
}

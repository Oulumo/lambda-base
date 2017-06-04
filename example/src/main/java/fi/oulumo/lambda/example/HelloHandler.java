package fi.oulumo.lambda.example;

import fi.oulumo.lambda.apigateway.handler.AbstractLambdaRequestHandler;
import fi.oulumo.lambda.apigateway.handler.HandlerContext;
import fi.oulumo.lambda.apigateway.handler.HandlerResponse;
import fi.oulumo.lambda.example.dto.Sentence;
import fi.oulumo.lambda.log.ILogger;
import fi.oulumo.lambda.log.LoggerFactory;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class HelloHandler extends AbstractLambdaRequestHandler<Void, Sentence> {
    private static final ILogger log = LoggerFactory.createLogger(HelloHandler.class);

    public static final String PARAMETER_NAME = "name";

    @Override
    protected HandlerResponse<Sentence> doHandleLambdaRequest(Void aVoid) {
        HandlerContext context = getHandlerContext();
        String nameParameter = context.getParameterValue(PARAMETER_NAME);

        log.debug("Hello handler called");
        log.debug(context.getAwsContext().getInvokedFunctionArn());

        return new HandlerResponse<>(new Sentence("Hello " + nameParameter + "!"));
    }

    @Override
    public Class<Void> getInputClass() {
        return null;
    }
}

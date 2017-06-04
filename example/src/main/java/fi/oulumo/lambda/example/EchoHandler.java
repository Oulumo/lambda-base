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
public class EchoHandler extends AbstractLambdaRequestHandler<Sentence, Sentence> {
    private static final ILogger log = LoggerFactory.createLogger(EchoHandler.class);

    @Override
    protected HandlerResponse<Sentence> doHandleLambdaRequest(Sentence input) {
        HandlerContext context = getHandlerContext();

        log.debug("Echo handler called");
        log.debug(context.getAwsContext().getInvokedFunctionArn());

        return new HandlerResponse<>(input);
    }

    @Override
    public Class<Sentence> getInputClass() {
        return Sentence.class;
    }
}

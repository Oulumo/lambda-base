package fi.oulumo.lambda.example;


import fi.oulumo.lambda.apigateway.handler.AbstractLambdaRequestHandler;
import fi.oulumo.lambda.apigateway.handler.HandlerResponse;
import fi.oulumo.lambda.example.dto.Info;
import fi.oulumo.lambda.log.ILogger;
import fi.oulumo.lambda.log.LoggerFactory;


/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class InfoHandler extends AbstractLambdaRequestHandler<Void, Info> {
    private static final ILogger log = LoggerFactory.createLogger(InfoHandler.class);

    private Info apiInfo = new Info(
            "Example API",
            "1.0.1",
            "Example lambda based API",
            "Example Maintainer <maintainer@example.com>",
            "(C) 2016 Example.com"
    );

    @Override
    protected HandlerResponse<Info> doHandleLambdaRequest(Void input) {
        log.trace("Info is about to be returned...");

        return new HandlerResponse<>(apiInfo);
    }

    @Override
    public Class<Void> getInputClass() {
        return null;
    }
}

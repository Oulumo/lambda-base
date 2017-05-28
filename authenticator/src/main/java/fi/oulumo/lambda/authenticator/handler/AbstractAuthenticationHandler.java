package fi.oulumo.lambda.authenticator.handler;

import com.amazonaws.services.lambda.runtime.Context;
import fi.oulumo.lambda.authenticator.AuthenticationMethodArn;
import fi.oulumo.lambda.authenticator.dto.AuthenticationRequest;
import fi.oulumo.lambda.authenticator.dto.AuthenticationResponse;
import fi.oulumo.lambda.core.buildinfo.BuildInfo;
import fi.oulumo.lambda.core.context.HandlerContextHolder;
import fi.oulumo.lambda.core.context.IHandlerContextDecorator;
import fi.oulumo.lambda.core.util.StageUtils;
import fi.oulumo.lambda.log.ILogger;
import fi.oulumo.lambda.log.LoggerFactory;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public abstract class AbstractAuthenticationHandler {
    public static final ILogger log = LoggerFactory.createLogger(AbstractAuthenticationHandler.class);
    private final IHandlerContextDecorator handlerContextDecorator;

    public AbstractAuthenticationHandler(IHandlerContextDecorator handlerContextDecorator) {
        this.handlerContextDecorator = handlerContextDecorator;
    }

    public AuthenticationResponse handleRequest(AuthenticationRequest input, Context context) {
        AuthenticationMethodArn methodArn = new AuthenticationMethodArn(input.getMethodArn());
        String stage = StageUtils.getCanonicalStageNameFrom(methodArn.getStage());

        HandlerContext handlerContext = new HandlerContext(context, stage);
        if (handlerContextDecorator != null) {
            handlerContextDecorator.decorateContext(handlerContext);
        }

        try {
            HandlerContextHolder.set(handlerContext);
            log.info(BuildInfo.getInfoString());

            return doHandleRequest(input);
        }
        finally {
            HandlerContextHolder.remove();
        }
    }

    protected abstract AuthenticationResponse doHandleRequest(AuthenticationRequest input);

    protected HandlerContext getHandlerContext() {
        return (HandlerContext) HandlerContextHolder.get();
    }
}

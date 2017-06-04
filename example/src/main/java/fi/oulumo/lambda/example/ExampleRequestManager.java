package fi.oulumo.lambda.example;

import com.google.gson.Gson;
import fi.oulumo.lambda.apigateway.AbstractLambdaRequestManager;
import fi.oulumo.lambda.apigateway.exception.IErrorTranslator;
import fi.oulumo.lambda.apigateway.handler.ILambdaRequestHandler;
import fi.oulumo.lambda.core.context.IHandlerContextDecorator;
import fi.oulumo.lambda.router.Router;
import fi.oulumo.lambda.router.regex.RegexMatchHelper;
import fi.oulumo.lambda.router.regex.RegexMatcher;
import fi.oulumo.lambda.router.route.HttpMethod;

import javax.inject.Inject;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class ExampleRequestManager extends AbstractLambdaRequestManager<RegexMatchHelper> {
    @Inject
    public ExampleRequestManager(Gson gson, IErrorTranslator errorTranslator, IHandlerContextDecorator handlerContextDecorator) {
        super(gson, errorTranslator, handlerContextDecorator);
    }

    @Override
    protected Router<RegexMatchHelper, ILambdaRequestHandler> setupRouter() {
        Router<RegexMatchHelper, ILambdaRequestHandler> retValue = new Router<>(new RegexMatcher<ILambdaRequestHandler>());

        retValue.defineRoute(HttpMethod.Get, "/info", new InfoHandler());
        retValue.defineRoute(HttpMethod.Get, "/error", new ErrorHandler());
        retValue.defineRoute(HttpMethod.Get, "/echo", new EchoHandler());
        retValue.defineRoute(HttpMethod.Get, "/hello/:" + HelloHandler.PARAMETER_NAME, new HelloHandler());

        return retValue;
    }

}

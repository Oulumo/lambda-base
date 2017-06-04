package fi.oulumo.lambda.example;

import com.google.gson.Gson;
import fi.oulumo.lambda.apigateway.exception.IErrorTranslator;
import fi.oulumo.lambda.apigateway.transformer.CorsTransformer;
import fi.oulumo.lambda.core.context.IHandlerContextDecorator;

import javax.inject.Inject;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class ExampleRequestManagerWithCors extends ExampleRequestManager {
    @Inject
    public ExampleRequestManagerWithCors(Gson gson, IErrorTranslator errorTranslator, IHandlerContextDecorator handlerContextDecorator) {
        super(gson, errorTranslator, handlerContextDecorator);
        setResponseTransformer(new CorsTransformer());
    }
}

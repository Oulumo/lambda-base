package fi.oulumo.lambda.apigateway.handler;

import com.google.gson.Gson;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public interface ILambdaRequestHandler<I, O> {
    Class<I> getInputClass();

    HandlerResponse<O> handleLambdaRequest(String requestInput, Gson gson);
}

package fi.oulumo.lambda.apigateway.exception;

import com.google.gson.JsonSyntaxException;
import fi.oulumo.lambda.apigateway.dto.response.HttpStatus;
import fi.oulumo.lambda.apigateway.handler.HandlerResponse;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class DefaultErrorTranslator implements IErrorTranslator {
    @Override
    public HandlerResponse<ErrorObject> translateError(Throwable t) {
        if (t instanceof LambdaException) {
            LambdaException lambdaException = (LambdaException) t;
            return new HandlerResponse<>(
                    new ErrorObject(lambdaException.getMessage()),
                    lambdaException.getStatusCode()
            );
        }
        else if (t instanceof JsonSyntaxException) {
            return new HandlerResponse<>(
                    new ErrorObject(HttpStatus.BadRequest.getTextMessage()),
                    HttpStatus.BadRequest
            );
        }
        else {
            return new HandlerResponse<>(
                    new ErrorObject(HttpStatus.InternalError.getTextMessage()),
                    HttpStatus.InternalError
            );
        }
    }
}

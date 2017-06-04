package fi.oulumo.lambda.example;

import fi.oulumo.lambda.apigateway.dto.response.HttpStatus;
import fi.oulumo.lambda.apigateway.exception.LambdaException;
import fi.oulumo.lambda.apigateway.handler.AbstractLambdaRequestHandler;
import fi.oulumo.lambda.apigateway.handler.HandlerResponse;
import fi.oulumo.lambda.example.dto.ErrorType;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class ErrorHandler extends AbstractLambdaRequestHandler<ErrorType, Void> {
    @Override
    protected HandlerResponse<Void> doHandleLambdaRequest(ErrorType input) {
        throw new LambdaException(input.isClient() ? HttpStatus.BadRequest : HttpStatus.NotImplemented);
    }

    @Override
    public Class<ErrorType> getInputClass() {
        return ErrorType.class;
    }
}

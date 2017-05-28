package fi.oulumo.lambda.apigateway.exception;

import fi.oulumo.lambda.apigateway.dto.response.HttpStatus;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class LambdaException extends RuntimeException{
    private HttpStatus statusCode;

    public LambdaException(HttpStatus statusCode) {
        this(statusCode, statusCode.getTextMessage());
    }

    public LambdaException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}

package fi.oulumo.lambda.apigateway.exception;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class ErrorObject {
    private String errorMessage;

    public ErrorObject(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

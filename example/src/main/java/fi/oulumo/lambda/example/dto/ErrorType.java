package fi.oulumo.lambda.example.dto;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class ErrorType {
    private boolean client;

    protected ErrorType() {
    }

    public ErrorType(boolean client) {
        this.client = client;
    }

    public boolean isClient() {
        return client;
    }
}

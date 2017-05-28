package fi.oulumo.lambda.authenticator.dto;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class AuthenticationRequest {
    private String type;
    private String authorizationToken;
    private String methodArn;

    protected AuthenticationRequest() {
        // To be used by serialization / IoC frameworks only.
    }

    public AuthenticationRequest(String type, String authorizationToken, String methodArn) {
        this.type = type;
        this.authorizationToken = authorizationToken;
        this.methodArn = methodArn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getMethodArn() {
        return methodArn;
    }

    public void setMethodArn(String methodArn) {
        this.methodArn = methodArn;
    }
}

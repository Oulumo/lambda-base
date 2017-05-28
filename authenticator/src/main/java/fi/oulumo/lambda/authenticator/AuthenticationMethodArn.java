package fi.oulumo.lambda.authenticator;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class AuthenticationMethodArn {
    private String region;
    private String awsAccountId;
    private String restApiId;
    private String stage;
    private String httpMethod;
    private String resource;

    public AuthenticationMethodArn(String compactForm) {
        String[] arnParts = compactForm.split(":");
        this.region = arnParts[3];
        this.awsAccountId = arnParts[4];

        String[] gatewayParts = arnParts[5].split("/");

        this.restApiId = gatewayParts[0];
        this.stage = gatewayParts[1];
        this.httpMethod = gatewayParts[2];

        if (gatewayParts.length == 4) {
            this.resource = gatewayParts[3];
        }
        else {
            this.resource = "";
        }
    }

    public String getRegion() {
        return region;
    }

    public String getAwsAccountId() {
        return awsAccountId;
    }

    public String getRestApiId() {
        return restApiId;
    }

    public String getStage() {
        return stage;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getResource() {
        return resource;
    }
}

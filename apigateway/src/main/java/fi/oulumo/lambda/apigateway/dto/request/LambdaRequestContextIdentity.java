package fi.oulumo.lambda.apigateway.dto.request;

import com.google.gson.annotations.SerializedName;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class LambdaRequestContextIdentity {
    @SerializedName("cognitoIdentityId")
    private String cognitoIdentityId;

    @SerializedName("cognitoIdentityPoolId")
    private String cognitoIdentityPoolId;

    @SerializedName("accountId")
    private String accountId;

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("cognitoAuthenticationProvider")
    private String cognitoAuthenticationProvider;

    @SerializedName("cognitoAuthenticationType")
    private String cognitoAuthenticationType;

    @SerializedName("sourceIp")
    private String sourceIp;

    @SerializedName("userAgent")
    private String userAgent;

    @SerializedName("userArn")
    private String userArn;

    private String user;

    private String caller;

    public String getCognitoIdentityId() {
        return cognitoIdentityId;
    }

    public void setCognitoIdentityId(String cognitoIdentityId) {
        this.cognitoIdentityId = cognitoIdentityId;
    }

    public String getCognitoIdentityPoolId() {
        return cognitoIdentityPoolId;
    }

    public void setCognitoIdentityPoolId(String cognitoIdentityPoolId) {
        this.cognitoIdentityPoolId = cognitoIdentityPoolId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCognitoAuthenticationProvider() {
        return cognitoAuthenticationProvider;
    }

    public void setCognitoAuthenticationProvider(String cognitoAuthenticationProvider) {
        this.cognitoAuthenticationProvider = cognitoAuthenticationProvider;
    }

    public String getCognitoAuthenticationType() {
        return cognitoAuthenticationType;
    }

    public void setCognitoAuthenticationType(String cognitoAuthenticationType) {
        this.cognitoAuthenticationType = cognitoAuthenticationType;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserArn() {
        return userArn;
    }

    public void setUserArn(String userArn) {
        this.userArn = userArn;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }
}

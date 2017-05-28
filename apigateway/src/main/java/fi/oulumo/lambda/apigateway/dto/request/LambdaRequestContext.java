package fi.oulumo.lambda.apigateway.dto.request;

import com.google.gson.annotations.SerializedName;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class LambdaRequestContext {
    @SerializedName("accountId")
    private String accountId;

    @SerializedName("apiId")
    private String apiId;

    @SerializedName("httpMethod")
    private String httpMethod;

    private String stage;

    @SerializedName("requestId")
    private String requestId;

    @SerializedName("resourceId")
    private String resourceId;

    @SerializedName("resourcePath")
    private String resourcePath;

    private LambdaRequestContextIdentity identity;

    private LambdaRequestContextAuthorizer authorizer;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public LambdaRequestContextIdentity getIdentity() {
        return identity;
    }

    public void setIdentity(LambdaRequestContextIdentity identity) {
        this.identity = identity;
    }

    public LambdaRequestContextAuthorizer getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(LambdaRequestContextAuthorizer authorizer) {
        this.authorizer = authorizer;
    }
}

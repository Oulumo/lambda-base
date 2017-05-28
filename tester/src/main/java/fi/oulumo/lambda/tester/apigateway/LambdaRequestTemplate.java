package fi.oulumo.lambda.tester.apigateway;

import fi.oulumo.lambda.apigateway.RoutingMethod;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequestContextIdentity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class LambdaRequestTemplate {
    public static final String PROXY_RESOURCE_DEFINITION = "/{proxy+}";
    public static final String DEFAULT_AUTHORIZATION_HEADER_NAME = "Authorization";

    private RoutingMethod routingMethod;
    private String resource;
    private String basePath;
    private Map<String, String> headers;
    private Map<String, String> stageVariables;
    private String accountId;
    private String apiId;
    private String resourceId;
    private String stage;
    private String authToken;
    private String authorizationHeaderName;
    private String authenticatedPrincipal;
    private String userAgent;
    private LambdaRequestContextIdentity identity;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    protected void addHeader(String name, String value) {
        if (this.headers == null) {
            this.headers = new HashMap<>();
        }

        this.headers.put(name, value);
    }

    public Map<String, String> getStageVariables() {
        return stageVariables;
    }

    public void setStageVariables(Map<String, String> stageVariables) {
        this.stageVariables = stageVariables;
    }

    protected void addStageVariable(String name, String value) {
        if (this.stageVariables == null) {
            this.stageVariables = new HashMap<>();
        }

        this.stageVariables.put(name, value);
    }

    public RoutingMethod getRoutingMethod() {
        return routingMethod;
    }

    public void setRoutingMethod(RoutingMethod routingMethod) {
        this.routingMethod = routingMethod;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

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

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthorizationHeaderName() {
        return authorizationHeaderName;
    }

    public void setAuthorizationHeaderName(String authorizationHeaderName) {
        this.authorizationHeaderName = authorizationHeaderName;
    }

    public String getAuthenticatedPrincipal() {
        return authenticatedPrincipal;
    }

    public void setAuthenticatedPrincipal(String authenticatedPrincipal) {
        this.authenticatedPrincipal = authenticatedPrincipal;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public LambdaRequestContextIdentity getIdentity() {
        return identity;
    }

    public void setIdentity(LambdaRequestContextIdentity identity) {
        this.identity = identity;
    }
}

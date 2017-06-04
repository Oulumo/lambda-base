package fi.oulumo.lambda.tester.apigateway;

import com.google.gson.Gson;
import fi.oulumo.lambda.apigateway.RoutingMethod;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequest;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequestContext;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequestContextAuthorizer;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequestContextIdentity;
import fi.oulumo.lambda.core.util.StringUtils;
import fi.oulumo.lambda.router.route.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class LambdaRequestBuilder {
    protected static final String PROXY_PATH_KEY = "proxy";

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

    private HttpMethod method;
    private String path;
    private Map<String, String> queryStringParameters;
    private String requestBody;

    public LambdaRequestBuilder() {
        this(new DefaultRequestTemplate());
    }

    public LambdaRequestBuilder(LambdaRequestTemplate template) {
        this.routingMethod = template.getRoutingMethod();
        this.resource = template.getResource();
        this.basePath = template.getBasePath();
        this.accountId = template.getAccountId();
        this.apiId = template.getApiId();
        this.resourceId = template.getResourceId();
        this.stage = template.getStage();
        this.authToken = template.getAuthToken();
        this.authorizationHeaderName = template.getAuthorizationHeaderName();
        this.authenticatedPrincipal = template.getAuthenticatedPrincipal();
        this.userAgent = template.getUserAgent();
        this.identity = null;

        this.headers = template.getHeaders();
        if (this.headers == null) {
            this.headers = new HashMap<>();
        }

        this.stageVariables = template.getStageVariables();
        if (this.stageVariables == null) {
            this.stageVariables = new HashMap<>();
        }
    }

    public LambdaRequestBuilder withHeader(String name, String value) {
        this.headers.put(name, value);

        return this;
    }

    public LambdaRequestBuilder withStageVariable(String name, String value) {
        this.stageVariables.put(name, value);

        return this;
    }

    public LambdaRequestBuilder withRoutingMethod(RoutingMethod routingMethod) {
        this.routingMethod = routingMethod;

        return this;
    }

    public LambdaRequestBuilder withResource(String resource) {
        this.resource = resource;

        return this;
    }

    public LambdaRequestBuilder withBasePath(String basePath) {
        this.basePath = basePath;

        return this;
    }

    public LambdaRequestBuilder withAccountId(String accountId) {
        this.accountId = accountId;

        return this;
    }

    public LambdaRequestBuilder withApiId(String apiId) {
        this.apiId = apiId;

        return this;
    }

    public LambdaRequestBuilder withResourceId(String resourceId) {
        this.resourceId = resourceId;

        return this;
    }

    public LambdaRequestBuilder withStage(String stage) {
        this.stage = stage;

        return this;
    }

    public LambdaRequestBuilder withAuthToken(String authToken) {
        this.authToken = authToken;

        return this;
    }

    public LambdaRequestBuilder withAuthorizationHeaderName(String authorizationHeaderName) {
        this.authorizationHeaderName = authorizationHeaderName;

        return this;
    }

    public LambdaRequestBuilder withAuthenticatedPrincipal(String authenticatedPrincipal) {
        this.authenticatedPrincipal = authenticatedPrincipal;

        return this;
    }

    public LambdaRequestBuilder withUserAgent(String userAgent) {
        this.userAgent = userAgent;

        return this;
    }

    public LambdaRequestBuilder withIdentity(LambdaRequestContextIdentity identity) {
        this.identity = identity;

        return this;
    }

    public LambdaRequestBuilder withMethod(HttpMethod method) {
        this.method = method;

        return this;
    }

    public LambdaRequestBuilder withPath(String path) {
        this.path = path;

        return this;
    }

    public LambdaRequestBuilder withQueryStringParameters(String name, String value) {
        if (StringUtils.hasText(name)) {
            if (queryStringParameters == null) {
                queryStringParameters = new HashMap<>();
            }

            queryStringParameters.put(name, value);
        }

        return this;
    }

    public LambdaRequestBuilder withRawJsonInput(String rawJsonInput) {
        this.requestBody = rawJsonInput;

        return this;
    }

    public LambdaRequestBuilder withInputObject(Object inputObject, Gson gson) {
        this.requestBody = gson.toJson(inputObject);

        return this;
    }

    public LambdaRequest build() {
        assertEnoughInformation();

        Map<String, String> requestHeaders = new HashMap<>(this.headers);
        if (this.authToken != null) {
            requestHeaders.put(this.authorizationHeaderName, this.authToken);
        }
        requestHeaders.put("User-Agent", this.userAgent);

        LambdaRequest retValue = new LambdaRequest();
        LambdaRequestContext requestContext = new LambdaRequestContext();
        retValue.setRequestContext(requestContext);

        retValue.setResource(combinePathElements(this.basePath, this.resource));
        retValue.setPath(combinePathElements(this.basePath, this.path));

        retValue.setHttpMethod(this.method.toString().toUpperCase());
        retValue.setHeaders(requestHeaders);
        retValue.setStageVariables(this.stageVariables);
        retValue.setQueryStringParameters(this.queryStringParameters);
        if (RoutingMethod.ProxyPath.equals(this.routingMethod)) {
            retValue.setPathParameters(getPathParametersFor(this.path));
        }

        requestContext.setAccountId(this.accountId);
        requestContext.setResourceId(this.resourceId);
        requestContext.setStage(this.stage);
        requestContext.setRequestId(UUID.randomUUID().toString());
        requestContext.setResourcePath(combinePathElements(this.basePath, this.resource));
        requestContext.setHttpMethod(this.method.toString().toUpperCase());
        requestContext.setApiId(this.apiId);

        if (this.identity != null) {
            requestContext.setIdentity(this.identity);
        }
        else {
            LambdaRequestContextIdentity id = new LambdaRequestContextIdentity();
            id.setAccountId(null);
            id.setCognitoIdentityPoolId(null);
            id.setCognitoIdentityId(null);
            id.setCaller(null);
            id.setApiKey(null);
            id.setSourceIp("127.0.0.1");
            id.setCognitoAuthenticationType(null);
            id.setCognitoAuthenticationProvider(null);
            id.setUserArn(null);
            id.setUserAgent(this.userAgent);
            id.setUser(null);

            requestContext.setIdentity(id);
        }

        if (this.authenticatedPrincipal != null) {
            LambdaRequestContextAuthorizer authorizer = new LambdaRequestContextAuthorizer();
            authorizer.setPrincipalId(this.authenticatedPrincipal);

            requestContext.setAuthorizer(authorizer);
        }

        if (StringUtils.hasText(this.requestBody)) {
            retValue.setRequestBody(this.requestBody);
        }
        else {
            retValue.setRequestBody(null);
        }

        return retValue;
    }

    private void assertEnoughInformation() {
        if (this.method == null) {
            throw new IllegalStateException("Request method must be specified.");
        }
        if (this.path == null) {
            throw new IllegalStateException("Path must be specified.");
        }
    }

    private Map<String, String> getPathParametersFor(String path) {
        Map<String, String> retValue = new HashMap<>();

        String proxyPath = (path.startsWith("/")) ? path.substring(1) : path;
        retValue.put(LambdaRequestBuilder.PROXY_PATH_KEY, proxyPath);

        return retValue;
    }

    private String combinePathElements(String basePath, String path) {
        StringBuilder retValue = new StringBuilder();
        if (StringUtils.hasText(basePath)) {
            retValue.append(basePath);
            if ((!basePath.endsWith("/")) && (!path.startsWith("/"))) {
                retValue.append("/");
            }
            retValue.append(path);
        }
        else {
            if (!path.startsWith("/")) {
                retValue.append("/");
            }
            retValue.append(path);
        }

        return retValue.toString();
    }
}

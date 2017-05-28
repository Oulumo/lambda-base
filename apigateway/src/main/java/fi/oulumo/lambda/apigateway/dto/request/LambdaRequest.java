package fi.oulumo.lambda.apigateway.dto.request;

import com.google.gson.annotations.SerializedName;
import fi.oulumo.lambda.apigateway.dto.HttpHeader;
import fi.oulumo.lambda.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class LambdaRequest {
    private String resource;

    private String path;

    @SerializedName("httpMethod")
    private String httpMethod;

    private Map<String, String> headers;

    @SerializedName("queryStringParameters")
    private Map<String, String> queryStringParameters;

    @SerializedName("pathParameters")
    private Map<String, String> pathParameters;

    @SerializedName("stageVariables")
    private Map<String, String> stageVariables;

    @SerializedName("requestContext")
    private LambdaRequestContext requestContext;

    @SerializedName("body")
    private String requestBody;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String name, String value) {
        if ((StringUtils.hasText(name)) && (StringUtils.hasText(value))) {
            if (headers == null) {
                headers = new HashMap<>();
            }

            headers.put(name, value);
        }
    }

    public String getHeaderValue(HttpHeader header) {
        if ((header != null) && (headers != null)){
            return headers.get(header.getHttpName());
        }

        return null;
    }

    public Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }

    public void setQueryStringParameters(Map<String, String> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    public void addQueryStringParameter(String name, String value) {
        if ((StringUtils.hasText(name)) && (StringUtils.hasText(value))) {
            if (queryStringParameters == null) {
                queryStringParameters = new HashMap<>();
            }

            queryStringParameters.put(name, value);
        }
    }

    public Map<String, String> getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
    }

    public void addPathParameters(Map<String, String> routerPathParameters) {
        if (routerPathParameters != null) {
            if (pathParameters == null) {
                pathParameters = new HashMap<>();
            }

            pathParameters.putAll(routerPathParameters);
        }
    }

    public void addPathParameter(String name, String value) {
        if ((StringUtils.hasText(name)) && (StringUtils.hasText(value))) {
            if (pathParameters == null) {
                pathParameters = new HashMap<>();
            }

            pathParameters.put(name, value);
        }
    }

    public Map<String, String> getStageVariables() {
        return stageVariables;
    }

    public void setStageVariables(Map<String, String> stageVariables) {
        this.stageVariables = stageVariables;
    }

    public void addStageVariable(String name, String value) {
        if ((StringUtils.hasText(name)) && (StringUtils.hasText(value))) {
            if (stageVariables == null) {
                stageVariables = new HashMap<>();
            }

            stageVariables.put(name, value);
        }
    }

    public LambdaRequestContext getRequestContext() {
        return requestContext;
    }

    public void setRequestContext(LambdaRequestContext requestContext) {
        this.requestContext = requestContext;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}

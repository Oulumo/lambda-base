package fi.oulumo.lambda.tester.apigateway;

import fi.oulumo.lambda.apigateway.RoutingMethod;

import java.util.HashMap;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class DefaultRequestTemplate extends LambdaRequestTemplate{
    public DefaultRequestTemplate() {
        setRoutingMethod(RoutingMethod.ProxyPath);
        setResource(LambdaRequestTemplate.PROXY_RESOURCE_DEFINITION);

        setBasePath("");
        setAccountId("account-00001");
        setApiId("api-00001");
        setResourceId("res001");
        setStage("dev");
        setAuthToken(null);
        setAuthorizationHeaderName(LambdaRequestTemplate.DEFAULT_AUTHORIZATION_HEADER_NAME);
        setAuthenticatedPrincipal(null);
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
        setIdentity(null);

        setupDefaultHeaders();
    }

    protected void setupDefaultHeaders() {
        addHeader("Accept", "application/json");
        addHeader("Accept-Encoding", "gzip, deflate, sdch, br");
        addHeader("Accept-Language", "en,en-US;q=0.8");
        addHeader("Cache-Control", "no-cache");
        addHeader("CloudFront-Forwarded-Proto", "https");
        addHeader("CloudFront-Is-Desktop-Viewer", "true");
        addHeader("CloudFront-Is-Mobile-Viewer", "false");
        addHeader("CloudFront-Is-SmartTV-Viewer", "false");
        addHeader("CloudFront-Is-Tablet-Viewer", "false");
        addHeader("CloudFront-Viewer-Country", "FI");
        addHeader("Content-Type", "application/json");
        addHeader("Host", "nosuchhost.execute-api.eu-central-1.amazonaws.com");

        addHeader("X-Forwarded-For", "192.168.1.1");
        addHeader("X-Forwarded-Port", "443");
        addHeader("X-Forwarded-Proto", "https");
    }
}

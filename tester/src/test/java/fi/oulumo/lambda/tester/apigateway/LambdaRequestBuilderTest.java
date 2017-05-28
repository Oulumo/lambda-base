package fi.oulumo.lambda.tester.apigateway;

import fi.oulumo.lambda.apigateway.dagger.ApiGatewayModule;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequest;
import fi.oulumo.lambda.router.route.HttpMethod;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class LambdaRequestBuilderTest {
    private static TestGsonProvider testProvider;

    @BeforeClass
    public static void setup() throws Exception {
        testProvider = DaggerTestGsonProvider.builder()
                .apiGatewayModule(new ApiGatewayModule())
                .build();
    }

    public static void tearDown() throws Exception {
    }

    @Test
    public void noInput() throws Exception {
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder();
        LambdaRequest request = requestBuilder.withMethod(HttpMethod.Get).withPath("first").build();

        assertThat(request, is(notNullValue()));
        assertThat(request.getPath(), is(equalTo("/first")));
        assertThat(request.getResource(), is(equalTo(LambdaRequestTemplate.PROXY_RESOURCE_DEFINITION)));
        assertThat(request.getPathParameters().get(LambdaRequestBuilder.PROXY_PATH_KEY), is(equalTo("first")));
        assertThat(request.getRequestBody(), is(nullValue()));
        assertThat(request.getRequestContext(), is(notNullValue()));
        assertThat(request.getRequestContext().getAuthorizer(), is(nullValue()));
        assertThat(request.getRequestContext().getIdentity(), is(notNullValue()));

        String requestString = testProvider.gson().toJson(request);
        assertThat(requestString, is(notNullValue()));
    }

    @Test
    public void noInput_withBasePath() throws Exception {
        LambdaRequestTemplate requestTemplate = new DefaultRequestTemplate();
        requestTemplate.setBasePath("/base");
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder(requestTemplate);
        LambdaRequest request = requestBuilder.withMethod(HttpMethod.Get).withPath("first").build();

        assertThat(request, is(notNullValue()));
        assertThat(request.getPath(), is(equalTo("/base/first")));
        assertThat(request.getResource(), is(equalTo("/base" + LambdaRequestTemplate.PROXY_RESOURCE_DEFINITION)));
        assertThat(request.getPathParameters().get(LambdaRequestBuilder.PROXY_PATH_KEY), is(equalTo("first")));
        assertThat(request.getRequestBody(), is(nullValue()));
        assertThat(request.getRequestContext(), is(notNullValue()));
        assertThat(request.getRequestContext().getAuthorizer(), is(nullValue()));
        assertThat(request.getRequestContext().getIdentity(), is(notNullValue()));

        String requestString = testProvider.gson().toJson(request);
        assertThat(requestString, is(notNullValue()));
    }

    @Test
    public void withInput() throws Exception {
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder();
        LambdaRequest request = requestBuilder
                .withMethod(HttpMethod.Get)
                .withPath("first")
                .withInputObject("TestObject", testProvider.gson())
                .build();

        assertThat(request, is(notNullValue()));
        assertThat(request.getPath(), is(equalTo("/first")));
        assertThat(request.getResource(), is(equalTo(LambdaRequestTemplate.PROXY_RESOURCE_DEFINITION)));
        assertThat(request.getPathParameters().get(LambdaRequestBuilder.PROXY_PATH_KEY), is(equalTo("first")));
        assertThat(request.getRequestBody(), is(equalTo("\"TestObject\"")));
        assertThat(request.getRequestContext(), is(notNullValue()));
        assertThat(request.getRequestContext().getAuthorizer(), is(nullValue()));
        assertThat(request.getRequestContext().getIdentity(), is(notNullValue()));


        String requestString = testProvider.gson().toJson(request);
        assertThat(requestString, is(notNullValue()));
    }

    @Test
    public void withInput_withBasePath() throws Exception {
        LambdaRequestTemplate requestTemplate = new DefaultRequestTemplate();
        requestTemplate.setBasePath("/base");
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder(requestTemplate);
        LambdaRequest request = requestBuilder
                .withMethod(HttpMethod.Get)
                .withPath("first")
                .withInputObject("TestObject", testProvider.gson())
                .build();

        assertThat(request, is(notNullValue()));
        assertThat(request.getPath(), is(equalTo("/base/first")));
        assertThat(request.getResource(), is(equalTo("/base" + LambdaRequestTemplate.PROXY_RESOURCE_DEFINITION)));
        assertThat(request.getPathParameters().get(LambdaRequestBuilder.PROXY_PATH_KEY), is(equalTo("first")));
        assertThat(request.getRequestBody(), is(equalTo("\"TestObject\"")));
        assertThat(request.getRequestContext(), is(notNullValue()));
        assertThat(request.getRequestContext().getAuthorizer(), is(nullValue()));
        assertThat(request.getRequestContext().getIdentity(), is(notNullValue()));

        String requestString = testProvider.gson().toJson(request);
        assertThat(requestString, is(notNullValue()));
    }

    @Test
    public void withInput_withAuthToken() throws Exception {
        LambdaRequestTemplate requestTemplate = new DefaultRequestTemplate();
        requestTemplate.setBasePath("/base");
        requestTemplate.setAuthToken("auth-TOKEN-value");
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder(requestTemplate);
        LambdaRequest request = requestBuilder
                .withMethod(HttpMethod.Get)
                .withPath("first")
                .withInputObject("TestObject", testProvider.gson())
                .build();

        assertThat(request, is(notNullValue()));
        assertThat(request.getPath(), is(equalTo("/base/first")));
        assertThat(request.getResource(), is(equalTo("/base" + LambdaRequestTemplate.PROXY_RESOURCE_DEFINITION)));
        assertThat(request.getPathParameters().get(LambdaRequestBuilder.PROXY_PATH_KEY), is(equalTo("first")));
        assertThat(request.getRequestBody(), is(equalTo("\"TestObject\"")));
        assertThat(request.getRequestContext(), is(notNullValue()));
        assertThat(request.getRequestContext().getAuthorizer(), is(nullValue()));
        assertThat(request.getRequestContext().getIdentity(), is(notNullValue()));
        assertThat(request.getHeaders().get("Authorization"), is(equalTo("auth-TOKEN-value")));

        String requestString = testProvider.gson().toJson(request);
        assertThat(requestString, is(notNullValue()));
    }

    @Test
    public void withInput_withAuthComponents() throws Exception {
        LambdaRequestTemplate requestTemplate = new DefaultRequestTemplate();
        requestTemplate.setBasePath("/base");
        requestTemplate.setAuthToken("auth-TOKEN-value");
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder(requestTemplate);
        LambdaRequest request = requestBuilder
                .withMethod(HttpMethod.Get)
                .withPath("first")
                .withInputObject("TestObject", testProvider.gson())
                .withAuthenticatedPrincipal("auth-principal-id")
                .build();

        assertThat(request, is(notNullValue()));
        assertThat(request.getPath(), is(equalTo("/base/first")));
        assertThat(request.getResource(), is(equalTo("/base" + LambdaRequestTemplate.PROXY_RESOURCE_DEFINITION)));
        assertThat(request.getPathParameters().get(LambdaRequestBuilder.PROXY_PATH_KEY), is(equalTo("first")));
        assertThat(request.getRequestBody(), is(equalTo("\"TestObject\"")));
        assertThat(request.getRequestContext(), is(notNullValue()));
        assertThat(request.getRequestContext().getAuthorizer(), is(notNullValue()));
        assertThat(request.getRequestContext().getAuthorizer().getPrincipalId(), is(equalTo("auth-principal-id")));
        assertThat(request.getRequestContext().getIdentity(), is(notNullValue()));
        assertThat(request.getHeaders().get("Authorization"), is(equalTo("auth-TOKEN-value")));

        String requestString = testProvider.gson().toJson(request);
        assertThat(requestString, is(notNullValue()));
    }
}
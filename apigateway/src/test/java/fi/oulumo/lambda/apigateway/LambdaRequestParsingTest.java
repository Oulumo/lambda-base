package fi.oulumo.lambda.apigateway;

import fi.oulumo.lambda.apigateway.dagger.ApiGatewayModule;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequest;
import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class LambdaRequestParsingTest {
    private static TestProvider testProvider;

    @BeforeClass
    public static void setup() throws Exception {
        testProvider = DaggerTestProvider.builder()
                .apiGatewayModule(new ApiGatewayModule())
                .build();
    }

    @Test
    public void testGetInput() throws IOException {
        InputStream input = getClass().getResourceAsStream("/proxy_inputs/input_get.json");
        String requestString = IOUtils.toString(input, Charset.defaultCharset());

        LambdaRequest request = testProvider.gson().fromJson(requestString, LambdaRequest.class);

        assertThat(request, is(notNullValue()));
        assertThat(request.getHttpMethod(), is(equalTo("GET")));
        assertThat(request.getPath(), is(equalTo("/main/login")));
        assertThat(request.getRequestContext().getAuthorizer(), is(nullValue()));
    }

    @Test
    public void testGetInputWithAuthorizer() throws IOException {
        InputStream input = getClass().getResourceAsStream("/proxy_inputs/input_get_with_authorizer.json");
        String requestString = IOUtils.toString(input, Charset.defaultCharset());

        LambdaRequest request = testProvider.gson().fromJson(requestString, LambdaRequest.class);

        assertThat(request, is(notNullValue()));
        assertThat(request.getHttpMethod(), is(equalTo("GET")));
        assertThat(request.getPath(), is(equalTo("/main/login")));
        assertThat(request.getRequestContext().getAuthorizer(), is(notNullValue()));
        assertThat(request.getRequestContext().getAuthorizer().getPrincipalId(), is(notNullValue()));
    }

    @Test
    public void testPostInput() throws IOException {
        InputStream input = getClass().getResourceAsStream("/proxy_inputs/input_post.json");
        String requestString = IOUtils.toString(input, Charset.defaultCharset());

        LambdaRequest request = testProvider.gson().fromJson(requestString, LambdaRequest.class);

        assertThat(request, is(notNullValue()));
        assertThat(request.getHttpMethod(), is(equalTo("POST")));
        assertThat(request.getPath(), is(equalTo("/main/login")));
        assertThat(request.getRequestContext().getAuthorizer(), is(nullValue()));
    }
}

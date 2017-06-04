package fi.oulumo.lambda.example.test;

import fi.oulumo.lambda.apigateway.dto.HttpHeader;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequest;
import fi.oulumo.lambda.example.dto.ErrorType;
import fi.oulumo.lambda.example.dto.Sentence;
import fi.oulumo.lambda.router.route.HttpMethod;
import fi.oulumo.lambda.tester.apigateway.LambdaRequestBuilder;
import fi.oulumo.lambda.tester.apigateway.RequestTester;
import fi.oulumo.lambda.tester.apigateway.TestResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static fi.oulumo.lambda.tester.apigateway.matchers.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class ExampleRequestRouterWithCorsTest {
    public static final String HTTP_ORIGIN_LOCALHOST = "http://localhost";
    private static TestExampleRequestManagerProvider testProvider;
    private static RequestTester requestTester;

    @BeforeClass
    public static void setup() throws Exception {
        testProvider = DaggerTestExampleRequestManagerProvider.builder()
                .build();
        requestTester = testProvider.testHelper().createTesterFor(testProvider.managerWithCors(), "example", "1.0.0");
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }

    @Test
    public void infoTest() throws Exception {
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder();
        LambdaRequest testRequest = requestBuilder.withMethod(HttpMethod.Get).withPath("/info").build();
        TestResponse testResponse = requestTester.sendRequest(testRequest);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(success()));
        assertThat(testResponse, is(withHttpCode(200)));
        assertThat(testResponse, is(withJsonContent("{\"name\":\"Example API\",\"version\":\"1.0.1\",\"description\":\"Example lambda based API\",\"contact_person\":\"Example Maintainer \\u003cmaintainer@example.com\\u003e\",\"license\":\"(C) 2016 Example.com\"}")));
        assertThat(testResponse, not(containsHeader(HttpHeader.CorsAllowOrigin.getHttpName())));
    }

    @Test
    public void infoTestWithOrigin() throws Exception {
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder();
        LambdaRequest testRequest = requestBuilder
                .withMethod(HttpMethod.Get)
                .withPath("/info")
                .withHeader(HttpHeader.Origin.getHttpName(), HTTP_ORIGIN_LOCALHOST)
                .build();
        TestResponse testResponse = requestTester.sendRequest(testRequest);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(success()));
        assertThat(testResponse, is(withHttpCode(200)));
        assertThat(testResponse, is(withJsonContent("{\"name\":\"Example API\",\"version\":\"1.0.1\",\"description\":\"Example lambda based API\",\"contact_person\":\"Example Maintainer \\u003cmaintainer@example.com\\u003e\",\"license\":\"(C) 2016 Example.com\"}")));
        assertThat(testResponse, is(containsHeaderWithValue(HttpHeader.CorsAllowOrigin.getHttpName(), HTTP_ORIGIN_LOCALHOST)));
    }

    @Test
    public void echoTest() throws Exception {
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder();
        LambdaRequest testRequest = requestBuilder
                .withMethod(HttpMethod.Get)
                .withPath("/echo")
                .withInputObject(new Sentence("Hello test"), testProvider.gson())
                .build();
        TestResponse testResponse = requestTester.sendRequest(testRequest);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(success()));
        assertThat(testResponse, is(withHttpCode(200)));
        assertThat(testResponse, is(withJsonContent("{\"sentence\":\"Hello test\"}")));
        assertThat(testResponse, not(containsHeader(HttpHeader.CorsAllowOrigin.getHttpName())));
    }

    @Test
    public void echoTestWithOrigin() throws Exception {
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder();
        LambdaRequest testRequest = requestBuilder
                .withMethod(HttpMethod.Get)
                .withPath("/echo")
                .withInputObject(new Sentence("Hello test"), testProvider.gson())
                .withHeader(HttpHeader.Origin.getHttpName(), HTTP_ORIGIN_LOCALHOST)
                .build();
        TestResponse testResponse = requestTester.sendRequest(testRequest);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(success()));
        assertThat(testResponse, is(withHttpCode(200)));
        assertThat(testResponse, is(withJsonContent("{\"sentence\":\"Hello test\"}")));
        assertThat(testResponse, is(containsHeaderWithValue(HttpHeader.CorsAllowOrigin.getHttpName(), HTTP_ORIGIN_LOCALHOST)));
    }

    @Test
    public void clientErrorTest() throws Exception {
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder();
        LambdaRequest testRequest = requestBuilder
                .withMethod(HttpMethod.Get)
                .withPath("/error")
                .withInputObject(new ErrorType(true), testProvider.gson())
                .build();

        TestResponse testResponse = requestTester.sendRequest(testRequest);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(clientError()));
        assertThat(testResponse, is(withHttpCode(400)));
        assertThat(testResponse, is(withJsonContent("{\"error_message\":\"Bad request\"}")));
        assertThat(testResponse, not(containsHeader(HttpHeader.CorsAllowOrigin.getHttpName())));
    }

    @Test
    public void clientErrorTestWithOrigin() throws Exception {
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder();
        LambdaRequest testRequest = requestBuilder
                .withMethod(HttpMethod.Get)
                .withPath("/error")
                .withInputObject(new ErrorType(true), testProvider.gson())
                .withHeader(HttpHeader.Origin.getHttpName(), HTTP_ORIGIN_LOCALHOST)
                .build();
        TestResponse testResponse = requestTester.sendRequest(testRequest);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(clientError()));
        assertThat(testResponse, is(withHttpCode(400)));
        assertThat(testResponse, is(withJsonContent("{\"error_message\":\"Bad request\"}")));
        assertThat(testResponse, is(containsHeader(HttpHeader.CorsAllowOrigin.getHttpName())));
    }

    @Test
    public void serverErrorTest() throws Exception {
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder();
        LambdaRequest testRequest = requestBuilder
                .withMethod(HttpMethod.Get)
                .withPath("/error")
                .withInputObject(new ErrorType(false), testProvider.gson())
                .build();
        TestResponse testResponse = requestTester.sendRequest(testRequest);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(serverError()));
        assertThat(testResponse, is(withHttpCode(501)));
        assertThat(testResponse, is(withJsonContent("{\"error_message\":\"Requested resource / method is not implemented\"}")));
        assertThat(testResponse, not(containsHeader(HttpHeader.CorsAllowOrigin.getHttpName())));
    }

    @Test
    public void serverErrorTestWithOrigin() throws Exception {
        LambdaRequestBuilder requestBuilder = new LambdaRequestBuilder();
        LambdaRequest testRequest = requestBuilder
                .withMethod(HttpMethod.Get)
                .withPath("/error")
                .withInputObject(new ErrorType(false), testProvider.gson())
                .withHeader(HttpHeader.Origin.getHttpName(), HTTP_ORIGIN_LOCALHOST)
                .build();
        TestResponse testResponse = requestTester.sendRequest(testRequest);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(serverError()));
        assertThat(testResponse, is(withHttpCode(501)));
        assertThat(testResponse, is(withJsonContent("{\"error_message\":\"Requested resource / method is not implemented\"}")));
        assertThat(testResponse, is(containsHeader(HttpHeader.CorsAllowOrigin.getHttpName())));
    }
}
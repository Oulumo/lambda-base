package fi.oulumo.lambda.tester.apigateway;

import com.amazonaws.util.StringInputStream;
import com.google.gson.Gson;
import fi.oulumo.lambda.apigateway.AbstractLambdaRequestManager;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequest;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * Locally executes a lambda request. No internet connection is required - unless the lambda request handlers
 * themselves use it.
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class RequestTester {
    private static Logger log = LoggerFactory.getLogger(RequestTester.class);

    private AbstractLambdaRequestManager requestManager;
    private TestContext testContext;
    private Gson gson;

    public RequestTester(AbstractLambdaRequestManager requestManager, Gson gson, String lambdaName, String lambdaVersion) {
        this(requestManager, gson, new TestContext(lambdaName, lambdaVersion));
    }

    public RequestTester(AbstractLambdaRequestManager requestManager, Gson gson, TestContext testContext) {
        this.requestManager = requestManager;
        this.testContext = testContext;
        this.gson = gson;
    }

    public LambdaRequestBuilder getLambdaRequestBuilder() {
        return new LambdaRequestBuilder();
    }

    public LambdaRequestBuilder getLambdaRequestBuilder(LambdaRequestTemplate template) {
        return new LambdaRequestBuilder(template);
    }

    public String sendRawRequest(String rawInput) {
        log.debug("Raw input: {}", rawInput);
        try {
            StringInputStream inputStream = new StringInputStream(rawInput);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ((TestLogCollector)testContext.getLogger()).clearLogMessages();

            requestManager.handleRequest(inputStream, outputStream, testContext);

            String retValue = outputStream.toString(Charset.defaultCharset());
            log.debug("Raw output: {}", retValue);

            return retValue;
        }
        catch (Throwable t) {
            log.debug("Invocation error.", t);

            return t.getMessage();
        }
    }

    public TestResponse sendRequest(LambdaRequest request) {
        String rawLambdaRequest = gson.toJson(request);

        String stringResponse =  sendRawRequest(rawLambdaRequest);

        if (stringResponse != null) {
            return gson.fromJson(stringResponse, TestResponse.class);
        }
        else {
            return null;
        }
    }
}
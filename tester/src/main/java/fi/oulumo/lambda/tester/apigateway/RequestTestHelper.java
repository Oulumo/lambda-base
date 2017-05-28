package fi.oulumo.lambda.tester.apigateway;

import com.google.gson.Gson;
import fi.oulumo.lambda.apigateway.AbstractLambdaRequestManager;

import javax.inject.Inject;

/**
 * Helps creating {@link RequestTester} instances, by taking care of sourcing the {@link Gson} instance
 * through Dagger.
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class RequestTestHelper {
    @Inject
    Gson gson;

    @Inject
    public RequestTestHelper() {
    }

    public RequestTester createTesterFor(AbstractLambdaRequestManager requestManager, String lambdaName, String lambdaVersion) {
        return new RequestTester(requestManager, gson, lambdaName, lambdaVersion);
    }

    public RequestTester createTesterFor(AbstractLambdaRequestManager requestManager, TestContext testContext) {
        return new RequestTester(requestManager, gson, testContext);
    }
}

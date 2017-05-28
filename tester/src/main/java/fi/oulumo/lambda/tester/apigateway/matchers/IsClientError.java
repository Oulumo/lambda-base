package fi.oulumo.lambda.tester.apigateway.matchers;

import fi.oulumo.lambda.tester.apigateway.TestResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class IsClientError extends TypeSafeDiagnosingMatcher<TestResponse> {
    @Override
    protected boolean matchesSafely(TestResponse item, Description mismatchDescription) {
        if ((item.getResponseCode() < 400) || (item.getResponseCode() > 499)){
            mismatchDescription
                    .appendText("was ")
                    .appendValue(item.getResponseCode())
                    .appendText(" which is not in the 4xx range");

            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a response with response code in the 4xx range");
    }

    public static IsClientError clientError() {
        return new IsClientError();
    }
}

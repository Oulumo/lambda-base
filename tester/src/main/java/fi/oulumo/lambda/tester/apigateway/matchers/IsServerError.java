package fi.oulumo.lambda.tester.apigateway.matchers;

import fi.oulumo.lambda.tester.apigateway.TestResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class IsServerError extends TypeSafeDiagnosingMatcher<TestResponse> {
    @Override
    protected boolean matchesSafely(TestResponse item, Description mismatchDescription) {
        if ((item.getResponseCode() < 500) || (item.getResponseCode() > 599)){
            mismatchDescription
                    .appendText("was ")
                    .appendValue(item.getResponseCode())
                    .appendText(" which is not in the 5xx range");

            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a response with response code in the 5xx range");
    }

    public static IsServerError serverError() {
        return new IsServerError();
    }
}

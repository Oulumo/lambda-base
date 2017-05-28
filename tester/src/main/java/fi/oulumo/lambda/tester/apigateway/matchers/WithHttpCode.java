package fi.oulumo.lambda.tester.apigateway.matchers;

import fi.oulumo.lambda.apigateway.dto.response.HttpStatus;
import fi.oulumo.lambda.tester.apigateway.TestResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class WithHttpCode extends TypeSafeDiagnosingMatcher<TestResponse> {
    private final int httpCode;

    public WithHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    @Override
    protected boolean matchesSafely(TestResponse item, Description mismatchDescription) {
        if (item.getResponseCode() != httpCode) {
            mismatchDescription
                    .appendText("was ")
                    .appendValue(item.getResponseCode())
                    .appendText(" which is not ")
                    .appendValue(httpCode);

            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a response with HTTP code ")
                .appendValue(httpCode);
    }

    public static WithHttpCode withHttpCode(int httpCode) {
        return new WithHttpCode(httpCode);
    }

    public static WithHttpCode withHttpStatus(HttpStatus status) {
        return new WithHttpCode(status.getCode());
    }
}

package fi.oulumo.lambda.tester.apigateway.matchers;

import fi.oulumo.lambda.tester.apigateway.TestResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Map;
import java.util.Set;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class ContainsHeader extends TypeSafeDiagnosingMatcher<TestResponse> {
    private final String expectedHeaderName;

    public ContainsHeader(String expectedHeaderName) {
        this.expectedHeaderName = expectedHeaderName;
    }

    @Override
    protected boolean matchesSafely(TestResponse item, Description mismatchDescription) {
        Map<String, String> headers = item.getHeaders();

        if ((headers == null) || (headers.size() <= 0)) {
            mismatchDescription
                    .appendText("contains no headers");

            return false;
        }
        else {
            Set<String> headerNames = headers.keySet();

            for (String headerName : headerNames) {
                if (headerName.equalsIgnoreCase(expectedHeaderName)) {
                    return true;
                }
            }

            mismatchDescription
                    .appendText("contains ")
                    .appendValue(headers.size())
                    .appendText(" header(s), but none matching ")
                    .appendValue(expectedHeaderName);

            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a response with ")
                .appendValue(expectedHeaderName)
                .appendText(" header defined");
    }

    public static ContainsHeader containsHeader(String headerName) {
        return new ContainsHeader(headerName);
    }
}

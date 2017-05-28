package fi.oulumo.lambda.tester.apigateway.matchers;

import fi.oulumo.lambda.tester.apigateway.TestResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Map;
import java.util.Set;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class ContainsHeaderWithValue extends TypeSafeDiagnosingMatcher<TestResponse> {
    private final String expectedHeaderName;
    private final String expectedValue;

    public ContainsHeaderWithValue(String expectedHeaderName, String expectedValue) {
        this.expectedHeaderName = expectedHeaderName;
        this.expectedValue = expectedValue;
    }

    @Override
    protected boolean matchesSafely(TestResponse item, Description mismatchDescription) {
        Map<String, String> headers = item.getHeaders();

        if ((headers == null) || (headers.size() <= 0)) {
            mismatchDescription
                    .appendText("contains no headers.");

            return false;
        }
        else {
            Set<Map.Entry<String, String>> headerDefinitions = headers.entrySet();

            for (Map.Entry<String, String> headerDefinition : headerDefinitions) {
                if (headerDefinition.getKey().equalsIgnoreCase(expectedHeaderName)) {
                    if (expectedValue.equals(headerDefinition.getValue())) {
                        return true;
                    }
                    else {
                        mismatchDescription
                                .appendText("contains \"")
                                .appendValue(expectedHeaderName)
                                .appendText(" header, but the value ")
                                .appendValue(headerDefinition.getValue())
                                .appendText(" doesn't match the expected ")
                                .appendValue(expectedValue);

                        return false;
                    }
                }
            }

            mismatchDescription
                    .appendText("contains ")
                    .appendValue(headers.size())
                    .appendText(" headers, but none matching ")
                    .appendValue(expectedHeaderName);

            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a response with ")
                .appendValue(expectedHeaderName)
                .appendText(" header defined, with value")
                .appendValue(expectedValue);
    }

    public static ContainsHeaderWithValue containsHeaderWithValue(String headerName, String value) {
        return new ContainsHeaderWithValue(headerName, value);
    }
}

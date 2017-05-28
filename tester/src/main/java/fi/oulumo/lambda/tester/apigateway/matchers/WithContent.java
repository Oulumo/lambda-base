package fi.oulumo.lambda.tester.apigateway.matchers;

import fi.oulumo.lambda.tester.apigateway.TestResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class WithContent extends TypeSafeDiagnosingMatcher<TestResponse> {
    private final String content;
    private final boolean ignoreCase;

    public WithContent(String content) {
        this(content, false);
    }

    public WithContent(String content, boolean ignoreCase) {
        this.content = content;
        this.ignoreCase = ignoreCase;
    }

    @Override
    protected boolean matchesSafely(TestResponse item, Description mismatchDescription) {
        if (!isEqualContent(this.content, item.getContent())) {
            mismatchDescription
                    .appendText("was ")
                    .appendValue(item.getContent())
                    .appendText(" which is not ")
                    .appendValue(content)
                    .appendText(", ignoring case: ")
                    .appendValue(ignoreCase);

            return false;
        }

        return true;
    }

    private boolean isEqualContent(String expectedContent, String actualContent) {
        if (expectedContent == null) {
            if (actualContent == null) {
                return true;
            }
        }
        else {
            if (this.ignoreCase) {
                return expectedContent.equalsIgnoreCase(actualContent);
            }
            else {
                return expectedContent.equals(actualContent);
            }
        }

        return false;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a response with content ")
                .appendValue(content)
                .appendText(", ignoring case: ")
                .appendValue(ignoreCase);
    }

    public static WithContent withContent(String content) {
        return new WithContent(content);
    }

    public static WithContent withContent(String content, boolean ignoreCase) {
        return new WithContent(content, ignoreCase);
    }
}

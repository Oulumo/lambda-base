package fi.oulumo.lambda.tester.apigateway.matchers;

import fi.oulumo.lambda.core.util.StringUtils;
import fi.oulumo.lambda.tester.apigateway.TestResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class WithContentMatchingRegex extends TypeSafeDiagnosingMatcher<TestResponse> {
    private final Pattern pattern;

    public WithContentMatchingRegex(String patternString) {
        this(Pattern.compile(patternString));
    }

    public WithContentMatchingRegex(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    protected boolean matchesSafely(TestResponse item, Description mismatchDescription) {
        if (StringUtils.hasText(item.getContent())) {
            Matcher matcher = pattern.matcher(item.getContent());
            if (!matcher.matches()) {
                mismatchDescription
                        .appendText("was ")
                        .appendValue(item.getContent())
                        .appendText(" which doesn't match the regex pattern");

                return false;
            }
        }
        else {
            mismatchDescription
                    .appendText("has no content");

            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a response with content matching the specified regex pattern");
    }

    public static WithContentMatchingRegex withContentMatchingRegex(String regex) {
        return new WithContentMatchingRegex(regex);
    }

    public static WithContentMatchingRegex withContentMatchingRegex(Pattern pattern) {
        return new WithContentMatchingRegex(pattern);
    }
}

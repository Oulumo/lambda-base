package fi.oulumo.lambda.tester.apigateway.matchers;

import fi.oulumo.lambda.tester.apigateway.TestResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class WithJsonContent extends TypeSafeDiagnosingMatcher<TestResponse> {
    private final String expectedJson;

    public WithJsonContent(String expectedJson) {
        this.expectedJson = expectedJson;
    }

    @Override
    protected boolean matchesSafely(TestResponse item, Description mismatchDescription) {
        if ((expectedJson == null) && (item.getContent() == null)) {
            return true;
        }
        else if (expectedJson == null) {
            mismatchDescription
                    .appendText("expected \"null\" but actual value is ")
                    .appendValue(item.getContent());

            return false;
        }
        else if (item.getContent() == null) {
            mismatchDescription
                    .appendText("expected ")
                    .appendValue(expectedJson)
                    .appendText(" but actual value is \"null\"");

            return false;
        }
        else {
            try {
                JSONCompareResult result = JSONCompare.compareJSON(expectedJson, item.getContent(), JSONCompareMode.LENIENT);
                if (!result.passed()) {
                    mismatchDescription
                            .appendText(result.getMessage());

                    return false;
                }
                else {
                    return true;
                }
            }
            catch (JSONException e) {
                mismatchDescription
                        .appendText("expected ")
                        .appendValue(expectedJson)
                        .appendText(" or actual ")
                        .appendValue(item.getContent())
                        .appendText(" value is not JSON");

                return false;
            }
        }
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a response with JSON content ")
                .appendValue(expectedJson);
    }

    public static WithJsonContent withJsonContent(String expectedJson) {
        return new WithJsonContent(expectedJson);
    }
}

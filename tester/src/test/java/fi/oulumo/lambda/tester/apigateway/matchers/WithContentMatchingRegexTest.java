package fi.oulumo.lambda.tester.apigateway.matchers;

import fi.oulumo.lambda.tester.apigateway.TestResponse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class WithContentMatchingRegexTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void withContentMatchingRegex() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("TeSt CoNtEnT");

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithContentMatchingRegex.withContentMatchingRegex("TeSt.*")));
    }

    @Test
    public void withContentMatchingRegex_nullFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent(null);

        thrown.expect(AssertionError.class);
        thrown.expectMessage("has no content");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithContentMatchingRegex.withContentMatchingRegex("TeSt.*")));
    }

    @Test
    public void withContentMatchingRegex_wrongFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("TeSt CoNtEnT");

        thrown.expect(AssertionError.class);
        thrown.expectMessage("which doesn't match the regex pattern");

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithContentMatchingRegex.withContentMatchingRegex("test.*")));
    }
}
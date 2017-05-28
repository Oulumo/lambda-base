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
public class WithContentTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void withContent() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("TeSt CoNtEnT");

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithContent.withContent("TeSt CoNtEnT")));
    }

    @Test
    public void withContent_ignoreCase() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("TeSt CoNtEnT");

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithContent.withContent("test content", true)));
    }

    @Test
    public void withContent_nullContent() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent(null);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithContent.withContent(null)));

    }

    @Test
    public void withContent_nullContentFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("TeSt CoNtEnT");

        thrown.expect(AssertionError.class);
        thrown.expectMessage("which is not");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithContent.withContent(null)));
    }

    @Test
    public void withContent_mismatchFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("TeSt CoNtEnT");

        thrown.expect(AssertionError.class);
        thrown.expectMessage("which is not");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithContent.withContent("Other content")));
    }

    @Test
    public void withContent_wrongCaseFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("TeSt CoNtEnT");

        thrown.expect(AssertionError.class);
        thrown.expectMessage("which is not");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithContent.withContent("test content")));
    }
}
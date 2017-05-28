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
public class WithJsonContentTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void withJsonContent() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("{\"data\" : \"value\", \"code\" : \"java\"}");

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithJsonContent.withJsonContent("{\"data\" : \"value\", \"code\" : \"java\"}")));
    }

    @Test
    public void withJsonContent_orderChanged() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("{\"data\" : \"value\", \"code\" : \"java\"}");

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithJsonContent.withJsonContent("{\"code\" : \"java\", \"data\" : \"value\"}")));
    }

    @Test
    public void withJsonContent_partialMatchMore() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("{\"code\" : \"java\", \"data\" : \"value\"}");

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithJsonContent.withJsonContent("{\"code\" : \"java\"}")));
    }

    @Test
    public void withJsonContent_bothNull() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent(null);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithJsonContent.withJsonContent(null)));
    }

    @Test
    public void withJsonContent_nullContentFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent(null);

        thrown.expect(AssertionError.class);
        thrown.expectMessage("but actual value is \"null\"");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithJsonContent.withJsonContent("{\"code\" : \"java\", \"data\" : \"value\"}")));
    }

    @Test
    public void withJsonContent_nullContentFails2() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("{\"code\" : \"java\", \"data\" : \"value\"}");

        thrown.expect(AssertionError.class);
        thrown.expectMessage("expected \"null\" but actual value is");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithJsonContent.withJsonContent(null)));
    }

    @Test
    public void withJsonContent_nonJsonFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("content");

        thrown.expect(AssertionError.class);
        thrown.expectMessage("value is not JSON");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithJsonContent.withJsonContent("{\"code\" : \"java\", \"data\" : \"value\"}")));
    }

    @Test
    public void withJsonContent_nonJsonFails2() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("{\"code\" : \"java\", \"data\" : \"value\"}");

        thrown.expect(AssertionError.class);
        thrown.expectMessage("value is not JSON");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithJsonContent.withJsonContent("content")));
    }

    @Test
    public void withJsonContent_mismatchFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("{\"code\" : \"java\", \"data\" : \"value\"}");

        thrown.expect(AssertionError.class);
        thrown.expectMessage("Expected: cat");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithJsonContent.withJsonContent("{\"cat\" : \"grumpy\"}")));
    }

    @Test
    public void withJsonContent_partialMatchLessFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setContent("{\"code\" : \"java\"}");

        thrown.expect(AssertionError.class);
        thrown.expectMessage("Expected: data");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithJsonContent.withJsonContent("{\"code\" : \"java\", \"data\" : \"value\"}")));
    }
}
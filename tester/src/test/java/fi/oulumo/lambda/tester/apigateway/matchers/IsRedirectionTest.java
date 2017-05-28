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
public class IsRedirectionTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void redirection() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(302);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsRedirection.redirection()));
    }

    @Test
    public void redirection_lowerLimit() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(300);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsRedirection.redirection()));
    }

    @Test
    public void redirection_upperLimit() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(399);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsRedirection.redirection()));
    }

    @Test
    public void redirection_bellowFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(299);

        thrown.expect(AssertionError.class);
        thrown.expectMessage("not in the 3xx range");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsRedirection.redirection()));
    }

    @Test
    public void redirection_aboveFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(400);

        thrown.expect(AssertionError.class);
        thrown.expectMessage("not in the 3xx range");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsRedirection.redirection()));
    }
}
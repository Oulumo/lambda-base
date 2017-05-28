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
public class IsServerErrorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void serverError() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(501);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsServerError.serverError()));
    }

    @Test
    public void serverError_lowerLimit() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(500);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsServerError.serverError()));
    }

    @Test
    public void serverError_upperLimit() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(599);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsServerError.serverError()));
    }

    @Test
    public void serverError_bellowFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(499);

        thrown.expect(AssertionError.class);
        thrown.expectMessage("not in the 5xx range");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsServerError.serverError()));
    }

    @Test
    public void serverError_aboveFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(600);

        thrown.expect(AssertionError.class);
        thrown.expectMessage("not in the 5xx range");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsServerError.serverError()));
    }
}
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
public class IsClientErrorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void clientError() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(404);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsClientError.clientError()));
    }

    @Test
    public void clientError_lowerLimit() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(400);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsClientError.clientError()));
    }

    @Test
    public void clientError_upperLimit() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(499);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsClientError.clientError()));
    }

    @Test
    public void clientError_bellowFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(399);

        thrown.expect(AssertionError.class);
        thrown.expectMessage("not in the 4xx range");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsClientError.clientError()));
    }

    @Test
    public void clientError_aboveFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(500);

        thrown.expect(AssertionError.class);
        thrown.expectMessage("not in the 4xx range");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsClientError.clientError()));
    }
}
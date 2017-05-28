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
public class IsSuccessTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void success() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(201);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsSuccess.success()));
    }

    @Test
    public void success_lowerLimit() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(200);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsSuccess.success()));
    }

    @Test
    public void success_upperLimit() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(299);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsSuccess.success()));
    }

    @Test
    public void success_bellowFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(199);

        thrown.expect(AssertionError.class);
        thrown.expectMessage("not in the 2xx range");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsSuccess.success()));
    }

    @Test
    public void success_aboveFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(300);

        thrown.expect(AssertionError.class);
        thrown.expectMessage("not in the 2xx range");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(IsSuccess.success()));
    }
}
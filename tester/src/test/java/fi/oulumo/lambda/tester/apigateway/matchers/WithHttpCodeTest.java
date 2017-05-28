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
public class WithHttpCodeTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void withHttpCode() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(404);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithHttpCode.withHttpCode(404)));
    }

    @Test
    public void withHttpCode_wrongFails() throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setResponseCode(405);

        thrown.expect(AssertionError.class);
        thrown.expectMessage("which is not");
        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(WithHttpCode.withHttpCode(404)));
    }
}
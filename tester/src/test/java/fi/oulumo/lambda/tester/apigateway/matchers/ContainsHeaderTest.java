package fi.oulumo.lambda.tester.apigateway.matchers;

import fi.oulumo.lambda.tester.apigateway.TestResponse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class ContainsHeaderTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void containsHeader() throws Exception {
        TestResponse testResponse = new TestResponse();
        Map<String, String> headers = new HashMap<>();
        headers.put("Location", "http://gmail.com");
        testResponse.setHeaders(headers);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(ContainsHeader.containsHeader("Location")));
    }

    @Test
    public void containsHeader_failsWrongName() throws Exception {
        TestResponse testResponse = new TestResponse();
        Map<String, String> headers = new HashMap<>();
        headers.put("Location", "http://gmail.com");
        testResponse.setHeaders(headers);

        assertThat(testResponse, is(notNullValue()));

        thrown.expect(AssertionError.class);
        thrown.expectMessage("but none matching");
        assertThat(testResponse, is(ContainsHeader.containsHeader("Accept")));
    }

    @Test
    public void containsHeader_failsNoHeaders() throws Exception {
        TestResponse testResponse = new TestResponse();

        assertThat(testResponse, is(notNullValue()));

        thrown.expect(AssertionError.class);
        thrown.expectMessage("contains no headers");
        assertThat(testResponse, is(ContainsHeader.containsHeader("Accept")));
    }
}
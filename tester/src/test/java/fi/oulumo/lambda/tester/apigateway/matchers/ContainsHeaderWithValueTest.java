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
public class ContainsHeaderWithValueTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void containsHeader() throws Exception {
        TestResponse testResponse = new TestResponse();
        Map<String, String> headers = new HashMap<>();
        headers.put("Location", "http://gmail.com");
        testResponse.setHeaders(headers);

        assertThat(testResponse, is(notNullValue()));
        assertThat(testResponse, is(ContainsHeaderWithValue.containsHeaderWithValue("Location", "http://gmail.com")));
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
        assertThat(testResponse, is(ContainsHeaderWithValue.containsHeaderWithValue("Accept", "application/json")));
    }

    @Test
    public void containsHeader_failsWrongContent() throws Exception {
        TestResponse testResponse = new TestResponse();
        Map<String, String> headers = new HashMap<>();
        headers.put("Location", "http://gmail.com");
        testResponse.setHeaders(headers);

        assertThat(testResponse, is(notNullValue()));

        thrown.expect(AssertionError.class);
        thrown.expectMessage("doesn't match the expected");
        assertThat(testResponse, is(ContainsHeaderWithValue.containsHeaderWithValue("Location", "http://gmails.com")));
    }

    @Test
    public void containsHeader_failsNoHeaders() throws Exception {
        TestResponse testResponse = new TestResponse();

        assertThat(testResponse, is(notNullValue()));

        thrown.expect(AssertionError.class);
        thrown.expectMessage("contains no headers");
        assertThat(testResponse, is(ContainsHeaderWithValue.containsHeaderWithValue("Location", "http://gmail.com")));
    }
}
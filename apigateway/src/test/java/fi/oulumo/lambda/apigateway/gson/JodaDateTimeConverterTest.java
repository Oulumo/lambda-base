package fi.oulumo.lambda.apigateway.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class JodaDateTimeConverterTest {
    @Test
    public void serializeAndDeserialize() throws Exception {
        DateTime now = DateTime.now();
        JodaDateTimeConverter converter = new JodaDateTimeConverter();

        JsonElement nowElement = converter.serialize(now, null, null);

        assertThat(nowElement, not(nullValue()));
        assertThat(nowElement, is(instanceOf(JsonPrimitive.class)));

        DateTime deserialized = converter.deserialize(nowElement, null, null);

        assertThat(deserialized, not(nullValue()));

        // This check is needed this way, as the converter doesn't take milliseconds
        Duration difference = new Duration(deserialized, now);
        assertThat(difference.getStandardSeconds(), is(lessThan((long)1)));
    }
}
package fi.oulumo.lambda.apigateway.gson;

import com.google.gson.*;
import fi.oulumo.lambda.core.util.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class JodaDateTimeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime>{
    private DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.basicDateTimeNoMillis();

    @Override
    public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
        if (src != null) {
            return new JsonPrimitive(dateTimeFormatter.print(src));
        }

        return null;
    }

    @Override
    public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (StringUtils.hasText(json.getAsString())) {
            return dateTimeFormatter.parseDateTime(json.getAsString());
        }

        return null;
    }
}

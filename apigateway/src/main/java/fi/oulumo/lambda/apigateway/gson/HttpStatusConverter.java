package fi.oulumo.lambda.apigateway.gson;

import com.google.gson.*;
import fi.oulumo.lambda.apigateway.dto.response.HttpStatus;

import java.lang.reflect.Type;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class HttpStatusConverter implements JsonSerializer<HttpStatus>, JsonDeserializer<HttpStatus> {
    @Override
    public JsonElement serialize(HttpStatus src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getCode());
    }

    @Override
    public HttpStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            int intValue = json.getAsInt();

            return HttpStatus.getFromCode(intValue);
        }
        catch (Exception e) {
            // Not an int value
        }

        return null;
    }
}

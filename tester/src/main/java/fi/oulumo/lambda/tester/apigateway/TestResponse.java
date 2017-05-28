package fi.oulumo.lambda.tester.apigateway;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class TestResponse {
    @SerializedName("headers")
    private Map<String, String> headers;

    @SerializedName("body")
    private String content;

    @SerializedName("statusCode")
    private int responseCode;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}

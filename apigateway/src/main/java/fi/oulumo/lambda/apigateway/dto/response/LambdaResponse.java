package fi.oulumo.lambda.apigateway.dto.response;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * The actual value that will be returned from the AWS Lambda function.
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class LambdaResponse {
    @SerializedName("statusCode")
    private HttpStatus status;

    @SerializedName("headers")
    private Map<String, String> headers;

    @SerializedName("body")
    private String content;

    /**
     * Constructor to be used by reflection based tools only (e.g.: IoC containers, JSon parsers, etc.).
     */
    protected LambdaResponse() {
    }

    public LambdaResponse(HttpStatus status, String content) {
        this.status = status;
        this.content = content;
    }

    public LambdaResponse(HttpStatus status, Map<String, String> headers, String content) {
        this.status = status;
        this.headers = headers;
        this.content = content;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

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
}

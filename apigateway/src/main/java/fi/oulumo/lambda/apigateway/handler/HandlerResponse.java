package fi.oulumo.lambda.apigateway.handler;

import fi.oulumo.lambda.apigateway.dto.HttpHeader;
import fi.oulumo.lambda.apigateway.dto.response.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class HandlerResponse<R> {
    private HttpStatus status;
    private Map<String, String> headers;
    private R value;

    public HandlerResponse(R value) {
        this(value, HttpStatus.OK, null);
    }

    public HandlerResponse(R value, HttpStatus status) {
        this(value, status, null);
    }

    public HandlerResponse(R value, HttpStatus status, Map<String, String> headers) {
        this.value = value;
        this.status = status;
        this.headers = headers;
    }

    public R getValue() {
        return value;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String name, String value) {
        if (headers == null) {
            headers = new HashMap<>();
        }

        headers.put(name, value);
    }

    public void removeHeader(String name) {
        if (headers != null) {
            headers.remove(name);
        }
    }

    public void clearHeaders() {
        headers = null;
    }

    /**
     * Creates a response with status code 301 (Moved Permanently) and the target url in
     * the "Location" header.
     */
    public static HandlerResponse<Void> movedPermanently(String urlString) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeader.Location.getHttpName(), urlString);

        return new HandlerResponse<>(null, HttpStatus.MovedPermanently, headers);
    }

    /**
     * Creates a response with status code 302 (Moved Temporarily / Found) and the target url in
     * the "Location" header.
     */
    public static HandlerResponse<Void> redirectTo(String urlString) {
        return movedTemporarily(urlString);
    }

    /**
     * Creates a response with status code 302 (Moved Temporarily / Found) and the target url in
     * the "Location" header. Originally if the request was a POST the redirection URL should be
     * contacted with a POST too, but usually it was implemented by browsers as 303
     * ({@link #seeOther(String)}. Since HTTP/1.1 method change is now allowed.
     */
    public static HandlerResponse<Void> movedTemporarily(String urlString) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeader.Location.getHttpName(), urlString);

        return new HandlerResponse<>(null, HttpStatus.MovedTemporarily, headers);
    }

    /**
     * Creates a response with status code 303 (See Other) and the target url in
     * the "Location" header. If the original method was POST the redirected URL is
     * supposed to be contacted with GET.
     */
    public static HandlerResponse<Void> seeOther(String urlString) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeader.Location.getHttpName(), urlString);

        return new HandlerResponse<>(null, HttpStatus.SeeOther, headers);
    }

    /**
     * Creates a response with status code 307 (Temporary Redirect) and the target url in
     * the "Location" header. The request method is not allowed to be changed when contacting
     * the redirection URL.
     */
    public static HandlerResponse<Void> temporaryRedirect(String urlString) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeader.Location.getHttpName(), urlString);

        return new HandlerResponse<>(null, HttpStatus.TemporaryRedirect, headers);
    }

    /**
     * Creates a response with status code 308 (Permanent Redirect) and the target url in
     * the "Location" header. The request method is not allowed to be changed when contacting
     * the redirection URL.
     */
    public static HandlerResponse<Void> permanentRedirect(String urlString) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeader.Location.getHttpName(), urlString);

        return new HandlerResponse<>(null, HttpStatus.PermanentRedirect, headers);
    }
}

package fi.oulumo.lambda.apigateway.dto;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public enum HttpHeader {
    ETag("ETag"),
    Expires("Expires"),
    LastModified("Last-Modified"),
    Location("Location"),
    RetryAfter("Retry-After"),
    Server("Server"),
    SetCookie("Set-Cookie"),
    Origin("Origin"),
    CorsAllowOrigin("Access-Control-Allow-Origin"),
    CorsAllowCredentials("Access-Control-Allow-Credentials"),
    CorsExposeHeaders("Access-Control-Expose-Headers");

    private String httpName;

    HttpHeader(String httpName) {
        this.httpName = httpName;
    }

    public String getHttpName() {
        return httpName;
    }

    public static HttpHeader getFromHttpName(String httpName) {
        for (HttpHeader header : HttpHeader.values()) {
            if (header.getHttpName().equalsIgnoreCase(httpName)) {
                return header;
            }
        }

        return null;
    }
}

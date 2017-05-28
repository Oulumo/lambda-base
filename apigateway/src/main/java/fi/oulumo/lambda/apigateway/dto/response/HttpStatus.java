package fi.oulumo.lambda.apigateway.dto.response;

/**
 * Constant definitions for standard HTTP status codes. Textual form is based on the IANA status code registry:
 * https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@SuppressWarnings("unused")
public enum HttpStatus {
    OK(200, "OK"),
    Created(201, "Entity created"),
    Accepted(202, "Accepted for background processing"),
    NoContent(204, "No content"),

    MovedPermanently(301, "Moved permanently"),
    MovedTemporarily(302, "Moved temporarily"),
    SeeOther(303, "See other"),
    TemporaryRedirect(307, "Temporary redirect"),
    PermanentRedirect (308, "Permanent redirect"),

    BadRequest(400, "Bad request"),
    Unauthorized(401, "Authorization is missing - no user credentials"),
    Forbidden(403, "User is not authorized for the resource / action"),
    NotFound(404, "Not found"),

    InternalError(500, "Internal server error"),
    NotImplemented(501, "Requested resource / method is not implemented"),
    Unavailable(503, "Requested resource / method is temporarily unavailable");

    protected int code;
    protected String textMessage;

    HttpStatus(int code, String textMessage) {
        this.code = code;
        this.textMessage = textMessage;
    }

    public int getCode() {
        return code;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public static HttpStatus getFromCode(int code) {
        for (HttpStatus status : HttpStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }

        return null;
    }
}

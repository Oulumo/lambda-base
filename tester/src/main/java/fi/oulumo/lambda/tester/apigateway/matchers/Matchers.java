package fi.oulumo.lambda.tester.apigateway.matchers;

import fi.oulumo.lambda.apigateway.dto.response.HttpStatus;

import java.util.regex.Pattern;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@SuppressWarnings("unused")
public class Matchers {
    public static ContainsHeader containsHeader(String headerName) {
        return ContainsHeader.containsHeader(headerName);
    }

    public static ContainsHeaderWithValue containsHeaderWithValue(String headerName, String value) {
        return ContainsHeaderWithValue.containsHeaderWithValue(headerName, value);
    }

    public static IsClientError clientError() {
        return IsClientError.clientError();
    }

    public static IsRedirection redirection() {
        return IsRedirection.redirection();
    }

    public static IsServerError serverError() {
        return IsServerError.serverError();
    }

    public static IsSuccess success() {
        return IsSuccess.success();
    }

    public static WithContent withContent(String content) {
        return WithContent.withContent(content);
    }

    public static WithContent withContent(String content, boolean ignoreCase) {
        return WithContent.withContent(content, ignoreCase);
    }

    public static WithContentMatchingRegex withContentMatchingRegex(String regex) {
        return WithContentMatchingRegex.withContentMatchingRegex(regex);
    }

    public static WithContentMatchingRegex withContentMatchingRegex(Pattern pattern) {
        return WithContentMatchingRegex.withContentMatchingRegex(pattern);
    }

    public static WithHttpCode withHttpCode(int httpCode) {
        return WithHttpCode.withHttpCode(httpCode);
    }

    public static WithHttpCode withHttpStatus(HttpStatus status) {
        return WithHttpCode.withHttpStatus(status);
    }

    public static WithJsonContent withJsonContent(String expectedJson) {
        return WithJsonContent.withJsonContent(expectedJson);
    }
}

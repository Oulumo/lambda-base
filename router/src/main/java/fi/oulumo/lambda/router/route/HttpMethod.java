package fi.oulumo.lambda.router.route;

import fi.oulumo.lambda.core.util.StringUtils;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public enum HttpMethod {
    Get, Put, Post, Delete, Head, Options;

    public static HttpMethod getFromString(String string) {
        if (StringUtils.hasText(string)) {
            for (HttpMethod method: HttpMethod.values()) {
                if (method.toString().equalsIgnoreCase(string)) {
                    return method;
                }
            }
        }

        return null;
    }
}

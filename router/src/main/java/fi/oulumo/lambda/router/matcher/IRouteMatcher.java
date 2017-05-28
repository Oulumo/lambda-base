package fi.oulumo.lambda.router.matcher;

import fi.oulumo.lambda.router.route.HttpMethod;
import fi.oulumo.lambda.router.route.RouteDefinition;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public interface IRouteMatcher<M, H> {
    String PATH_SEPARATOR = "/";
    String PARAMETER_PREFIX = ":";
    String ANY_SINGLE = "*";
    String ALL = "**";

    RouteDefinition<M, H> createRouteDefinitionFrom(HttpMethod httpMethod, String pathPattern, H handler);

    RouteMatchResult matches(RouteDefinition<M, H> routeDefinition, HttpMethod httpMethod, String path);
}

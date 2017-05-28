package fi.oulumo.lambda.router;


import fi.oulumo.lambda.core.util.StringUtils;
import fi.oulumo.lambda.log.ILogger;
import fi.oulumo.lambda.log.LoggerFactory;
import fi.oulumo.lambda.router.matcher.IRouteMatcher;
import fi.oulumo.lambda.router.matcher.RouteMatchResult;
import fi.oulumo.lambda.router.route.HttpMethod;
import fi.oulumo.lambda.router.route.RouteDefinition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class Router<M, H> {
    private static final ILogger log = LoggerFactory.createLogger(Router.class);

    private List<RouteDefinition<M, H>> routeDefinitions;
    private IRouteMatcher<M, H> routeMatcher;

    public Router(IRouteMatcher<M, H> routeMatcher) {
        this.routeMatcher = routeMatcher;
        this.routeDefinitions = new ArrayList<>();
    }

    public void defineRoute(HttpMethod httpMethod, String pathPattern, H handler) {
        RouteDefinition<M, H> newDefinition = routeMatcher.createRouteDefinitionFrom(httpMethod, pathPattern, handler);

        if (newDefinition != null) {
            log.debug("New route added for %1$s with path pattern: %2$s", httpMethod.name(), pathPattern);
            routeDefinitions.add(newDefinition);
        }
    }

    public void removeRoutesMatching(HttpMethod httpMethod, String path) {
        Iterator<RouteDefinition<M, H>> routeDefinitionIterator = routeDefinitions.iterator();
        while (routeDefinitionIterator.hasNext()) {
            RouteDefinition<M, H> definition = routeDefinitionIterator.next();

            HttpMethod methodToMatch = (httpMethod == null) ? definition.getHttpMethod() : httpMethod;
            RouteMatchResult result = routeMatcher.matches(definition, methodToMatch, path);

            if (result.isMatched()) {
                log.debug("Removing route definition: %1$s", definition.toString());
                routeDefinitionIterator.remove();
            }
        }
    }

    public void clearRoutes() {
        log.debug("Routes are cleared.");
        routeDefinitions.clear();
    }

    public RouterMatchResult<H> findHandlerFor(HttpMethod httpMethod, String path) {
        if ((httpMethod != null) && (StringUtils.hasText(path))) {
            for (RouteDefinition<M, H> definition: routeDefinitions) {
                RouteMatchResult result = routeMatcher.matches(definition, httpMethod, path);

                if (result.isMatched()) {
                    return new RouterMatchResult<>(definition.getHandlerObject(), result.getPathParameters());
                }
            }
        }

        return null;
    }
}

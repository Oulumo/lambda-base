package fi.oulumo.lambda.router.matcher;

import java.util.Map;
import java.util.Optional;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class RouteMatchResult {
    public static final RouteMatchResult NO_MATCH = new RouteMatchResult(false, null);
    public static final RouteMatchResult MATCH_NO_PARAMETERS = new RouteMatchResult(true, null);

    private boolean matched;
    private Optional<Map<String, String>> pathParameters;

    public RouteMatchResult(boolean matched, Map<String, String> pathParameters) {
        this.matched = matched;
        this.pathParameters = ((pathParameters == null) || (pathParameters.isEmpty())) ? Optional.empty() : Optional.of(pathParameters);
    }

    public boolean isMatched() {
        return matched;
    }

    public Optional<Map<String, String>> getPathParameters() {
        return pathParameters;
    }
}

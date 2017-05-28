package fi.oulumo.lambda.router;

import java.util.Map;
import java.util.Optional;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class RouterMatchResult<H> {
    private H handler;
    private Optional<Map<String, String>> pathParameters;

    public RouterMatchResult(H handler, Optional<Map<String, String>> pathParameters) {
        this.handler = handler;
        this.pathParameters = pathParameters;
    }

    public H getHandler() {
        return handler;
    }

    public Optional<Map<String, String>> getPathParameters() {
        return pathParameters;
    }
}

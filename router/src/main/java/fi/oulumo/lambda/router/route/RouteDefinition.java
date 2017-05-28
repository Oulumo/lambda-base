package fi.oulumo.lambda.router.route;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class RouteDefinition<M, H> {
    private HttpMethod httpMethod;
    private M matcherHelpObject;
    private H handlerObject;

    public RouteDefinition(HttpMethod httpMethod, M matcherHelpObject, H handlerObject) {
        this.httpMethod = httpMethod;
        this.matcherHelpObject = matcherHelpObject;
        this.handlerObject = handlerObject;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public M getMatcherHelpObject() {
        return matcherHelpObject;
    }

    public H getHandlerObject() {
        return handlerObject;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RouteDefinition{");
        sb.append("httpMethod=").append(httpMethod);
        sb.append(", matcherHelpObject=").append(matcherHelpObject);
        sb.append(", handlerObject=").append(handlerObject);
        sb.append('}');

        return sb.toString();
    }
}

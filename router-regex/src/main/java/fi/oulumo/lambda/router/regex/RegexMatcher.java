package fi.oulumo.lambda.router.regex;

import fi.oulumo.lambda.core.util.StringUtils;
import fi.oulumo.lambda.router.matcher.IRouteMatcher;
import fi.oulumo.lambda.router.matcher.RouteMatchResult;
import fi.oulumo.lambda.router.route.HttpMethod;
import fi.oulumo.lambda.router.route.RouteDefinition;
import fi.oulumo.lambda.router.util.PathUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class RegexMatcher<H> implements IRouteMatcher<RegexMatchHelper, H> {
    private static final String REGEXP_START = "[/]?";
    private static final String PARAMETER_REGEXP_GROUP = "([^/]+)";
    private static final String ANY_SINGLE_REGEXP = "[^/]+";
    private static final String ALL_REGEXP = ".*?";

    @Override
    public RouteDefinition<RegexMatchHelper, H> createRouteDefinitionFrom(HttpMethod httpMethod, String pathPattern, H handler) {
        if (StringUtils.hasText(pathPattern) && (httpMethod != null) && (handler != null)) {
            List<String> pathElements = PathUtil.splitToElements(pathPattern);

            if (pathElements != null) {
                RegexMatchHelper helper = new RegexMatchHelper();
                StringBuilder patternString = new StringBuilder(REGEXP_START);
                boolean firstElement = true;

                for(String pathElement : pathElements) {
                    if (firstElement) {
                        firstElement = false;
                    }
                    else {
                        patternString.append(PATH_SEPARATOR);
                    }

                    if (pathElement.equals(ANY_SINGLE)) {
                        patternString.append(ANY_SINGLE_REGEXP);
                    }
                    else if (pathElement.equals(ALL)) {
                        patternString.append(ALL_REGEXP);
                    }
                    else if (pathElement.startsWith(PARAMETER_PREFIX)) {
                        helper.addParameterName(pathElement.substring(1));
                        patternString.append(PARAMETER_REGEXP_GROUP);
                    }
                    else {
                        patternString.append(pathElement);
                    }
                }

                helper.setRegexPattern(Pattern.compile(patternString.toString()));

                return new RouteDefinition<>(httpMethod, helper, handler);
            }
        }

        return null;
    }

    @Override
    public RouteMatchResult matches(RouteDefinition<RegexMatchHelper, H> routeDefinition, HttpMethod httpMethod, String path) {
        if (routeDefinition.getHttpMethod().equals(httpMethod)) {
            if (StringUtils.hasText(path)) {
                String cleanPath = path.trim();

                Matcher pathMatcher = routeDefinition.getMatcherHelpObject().getRegexPattern().matcher(cleanPath);

                if (pathMatcher.matches()) {
                    if (routeDefinition.getMatcherHelpObject().hasParameterNames()) {
                        List<String> parameterNames = routeDefinition.getMatcherHelpObject().getParameterNames();
                        Map<String, String> pathParameterValues = new HashMap<>(parameterNames.size());
                        int counter = 1;

                        for (String parameterName : parameterNames) {
                            pathParameterValues.put(parameterName, pathMatcher.group(counter));

                            counter++;
                        }

                        return new RouteMatchResult(true, pathParameterValues);
                    }
                    else {
                        return RouteMatchResult.MATCH_NO_PARAMETERS;
                    }
                }
            }
        }

        return RouteMatchResult.NO_MATCH;
    }
}

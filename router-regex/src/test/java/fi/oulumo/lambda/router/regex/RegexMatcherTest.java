package fi.oulumo.lambda.router.regex;

import fi.oulumo.lambda.router.matcher.RouteMatchResult;
import fi.oulumo.lambda.router.route.HttpMethod;
import fi.oulumo.lambda.router.route.RouteDefinition;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class RegexMatcherTest {
    @Test
    public void simpleRouteTest_match() throws Exception {
        RegexMatcher<String> matcher = new RegexMatcher<>();

        RouteDefinition<RegexMatchHelper, String> route = matcher.createRouteDefinitionFrom(HttpMethod.Get, "/user/:id/image", "Nothing");

        assertThat(route, is(notNullValue()));

        RouteMatchResult matchResult = matcher.matches(route, HttpMethod.Get, "user/johndoe/image");

        assertThat(matchResult, is(notNullValue()));
        assertThat(matchResult.isMatched(), is(true));
        assertThat(matchResult.getPathParameters().isPresent(), is(true));

        Map<String, String> pathParameters = matchResult.getPathParameters().get();

        assertThat(pathParameters.get("id"), is("johndoe"));
    }

    @Test
    public void simpleRouteTest_matchAnySingle() throws Exception {
        RegexMatcher<String> matcher = new RegexMatcher<>();

        RouteDefinition<RegexMatchHelper, String> route = matcher.createRouteDefinitionFrom(HttpMethod.Get, "/user/*/image", "Nothing");

        assertThat(route, is(notNullValue()));

        RouteMatchResult matchResult = matcher.matches(route, HttpMethod.Get, "user/johndoe/image");

        assertThat(matchResult, is(notNullValue()));
        assertThat(matchResult.isMatched(), is(true));
        assertThat(matchResult.getPathParameters().isPresent(), is(false));
    }

    @Test
    public void simpleRouteTest_matchAnyMultiple() throws Exception {
        RegexMatcher<String> matcher = new RegexMatcher<>();

        RouteDefinition<RegexMatchHelper, String> route = matcher.createRouteDefinitionFrom(HttpMethod.Get, "/user/*/image", "Nothing");

        assertThat(route, is(notNullValue()));

        RouteMatchResult matchResult = matcher.matches(route, HttpMethod.Get, "user/john/doe/image");

        assertThat(matchResult, is(notNullValue()));
        assertThat(matchResult.isMatched(), is(false));
    }

    @Test
    public void simpleRouteTest_matchAllSingle() throws Exception {
        RegexMatcher<String> matcher = new RegexMatcher<>();

        RouteDefinition<RegexMatchHelper, String> route = matcher.createRouteDefinitionFrom(HttpMethod.Get, "/user/**/image", "Nothing");

        assertThat(route, is(notNullValue()));

        RouteMatchResult matchResult = matcher.matches(route, HttpMethod.Get, "user/johndoe/image");

        assertThat(matchResult, is(notNullValue()));
        assertThat(matchResult.isMatched(), is(true));
        assertThat(matchResult.getPathParameters().isPresent(), is(false));
    }

    @Test
    public void simpleRouteTest_matchAllMultiple() throws Exception {
        RegexMatcher<String> matcher = new RegexMatcher<>();

        RouteDefinition<RegexMatchHelper, String> route = matcher.createRouteDefinitionFrom(HttpMethod.Get, "/user/**/image", "Nothing");

        assertThat(route, is(notNullValue()));

        RouteMatchResult matchResult = matcher.matches(route, HttpMethod.Get, "user/john/doe/image");

        assertThat(matchResult, is(notNullValue()));
        assertThat(matchResult.isMatched(), is(true));
        assertThat(matchResult.getPathParameters().isPresent(), is(false));
    }

    @Test
    public void simpleRouteTest_matchAllMultipleAlmost() throws Exception {
        RegexMatcher<String> matcher = new RegexMatcher<>();

        RouteDefinition<RegexMatchHelper, String> route = matcher.createRouteDefinitionFrom(HttpMethod.Get, "/user/**/image", "Nothing");

        assertThat(route, is(notNullValue()));

        RouteMatchResult matchResult = matcher.matches(route, HttpMethod.Get, "user/john/doe/images");

        assertThat(matchResult, is(notNullValue()));
        assertThat(matchResult.isMatched(), is(false));
    }

    @Test
    public void simpleRouteTest_matchSpecialChars() throws Exception {
        RegexMatcher<String> matcher = new RegexMatcher<>();

        RouteDefinition<RegexMatchHelper, String> route = matcher.createRouteDefinitionFrom(HttpMethod.Get, "/user/:id/image", "Nothing");

        assertThat(route, is(notNullValue()));

        RouteMatchResult matchResult = matcher.matches(route, HttpMethod.Get, "user/123e4567-e89b-12d3-a456-426655440000/image");

        assertThat(matchResult, is(notNullValue()));
        assertThat(matchResult.isMatched(), is(true));
        assertThat(matchResult.getPathParameters().isPresent(), is(true));

        Map<String, String> pathParameters = matchResult.getPathParameters().get();

        assertThat(pathParameters.get("id"), is("123e4567-e89b-12d3-a456-426655440000"));
    }

    @Test
    public void simpleRouteTest_noMatch() throws Exception {
        RegexMatcher<String> matcher = new RegexMatcher<>();

        RouteDefinition<RegexMatchHelper, String> route = matcher.createRouteDefinitionFrom(HttpMethod.Get, "/user/:id/image", "Nothing");

        assertThat(route, is(notNullValue()));

        RouteMatchResult matchResult = matcher.matches(route, HttpMethod.Get, "users/johndoe/image");

        assertThat(matchResult, is(notNullValue()));
        assertThat(matchResult.isMatched(), is(false));
    }

    @Test
    public void simpleRouteTest_noMatchMethod() throws Exception {
        RegexMatcher<String> matcher = new RegexMatcher<>();

        RouteDefinition<RegexMatchHelper, String> route = matcher.createRouteDefinitionFrom(HttpMethod.Get, "/user/:id/image", "Nothing");

        assertThat(route, is(notNullValue()));

        RouteMatchResult matchResult = matcher.matches(route, HttpMethod.Post, "user/johndoe/image");

        assertThat(matchResult, is(notNullValue()));
        assertThat(matchResult.isMatched(), is(false));
        assertThat(matchResult.getPathParameters().isPresent(), is(false));
    }

    @Test
    public void simpleRouteTest_noMatchMissing() throws Exception {
        RegexMatcher<String> matcher = new RegexMatcher<>();

        RouteDefinition<RegexMatchHelper, String> route = matcher.createRouteDefinitionFrom(HttpMethod.Get, "/user/:id/image", "Nothing");

        assertThat(route, is(notNullValue()));

        RouteMatchResult matchResult = matcher.matches(route, HttpMethod.Get, "user//image");

        assertThat(matchResult, is(notNullValue()));
        assertThat(matchResult.isMatched(), is(false));
    }

    @Test
    public void simpleRouteTest_noMatchShort() throws Exception {
        RegexMatcher<String> matcher = new RegexMatcher<>();

        RouteDefinition<RegexMatchHelper, String> route = matcher.createRouteDefinitionFrom(HttpMethod.Get, "/user/:id/image", "Nothing");

        assertThat(route, is(notNullValue()));

        RouteMatchResult matchResult = matcher.matches(route, HttpMethod.Get, "user/johndoe/");

        assertThat(matchResult, is(notNullValue()));
        assertThat(matchResult.isMatched(), is(false));
    }
}
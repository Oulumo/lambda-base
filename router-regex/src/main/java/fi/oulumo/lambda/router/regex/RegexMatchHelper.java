package fi.oulumo.lambda.router.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class RegexMatchHelper {
    private List<String> parameterNames;
    private Pattern regexPattern;

    public RegexMatchHelper() {
        this.parameterNames = new ArrayList<>();
    }

    public List<String> getParameterNames() {
        return parameterNames;
    }

    public void addParameterName(String name) {
        parameterNames.add(name);
    }

    public boolean hasParameterNames() {
        return (parameterNames.size() > 0);
    }

    public Pattern getRegexPattern() {
        return regexPattern;
    }

    public void setRegexPattern(Pattern regexPattern) {
        this.regexPattern = regexPattern;
    }
}

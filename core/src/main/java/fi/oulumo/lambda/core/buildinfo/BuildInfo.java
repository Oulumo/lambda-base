package fi.oulumo.lambda.core.buildinfo;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@SuppressWarnings("ALL")
public class BuildInfo {
    private static final String VERSION_PROPERTY = "build.version";
    private static final String DATE_PROPERTY = "build.date";
    private static final String USER_PROPERTY = "build.user";
    private static final String NAME_PROPERTY = "lambda.name";

    private static final String UNDEFINED_VALUE = "Undefined";
    private static final String NO_INFO_STRING = "No lambda build information is available";
    private static final String INFO_STRING_FORMAT = "%s lambda v%s (built on: %s by %s)";

    private static Properties buildProperties;
    private static String builtBuildInfo;

    static {
        builtBuildInfo = null;
        buildProperties = new Properties();

        try {
            InputStream inStream = BuildInfo.class.getResourceAsStream("/buildinfo.properties");
            if (inStream != null) {
                buildProperties.load(inStream);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            // Just ignore it
            buildProperties = null;
        }
    }

    private BuildInfo() {
        // Utility class, no instances are allowed
    }

    public static String getBuildVersion() {
        if (buildProperties != null) {
            return buildProperties.getProperty(VERSION_PROPERTY, UNDEFINED_VALUE);
        }

        return UNDEFINED_VALUE;
    }

    public static String getBuildDate() {
        if (buildProperties != null) {
            return buildProperties.getProperty(DATE_PROPERTY, UNDEFINED_VALUE);
        }

        return UNDEFINED_VALUE;
    }

    public static String getBuildUser() {
        if (buildProperties != null) {
            return buildProperties.getProperty(USER_PROPERTY, UNDEFINED_VALUE);
        }

        return UNDEFINED_VALUE;
    }

    public static String getLambdaName() {
        if (buildProperties != null) {
            return buildProperties.getProperty(NAME_PROPERTY, UNDEFINED_VALUE);
        }

        return UNDEFINED_VALUE;
    }

    public static String getInfoString() {
        if (builtBuildInfo == null) {
            if (buildProperties == null) {
                builtBuildInfo = NO_INFO_STRING;
            }
            else {
                builtBuildInfo = String.format(INFO_STRING_FORMAT, getLambdaName(), getBuildVersion(), getBuildDate(), getBuildUser());
            }
        }

        return builtBuildInfo;
    }
}

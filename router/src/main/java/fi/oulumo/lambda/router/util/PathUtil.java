package fi.oulumo.lambda.router.util;


import fi.oulumo.lambda.core.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static fi.oulumo.lambda.router.matcher.IRouteMatcher.PATH_SEPARATOR;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class PathUtil {

    private PathUtil() {
        // Utility class, no instances are allowed
    }

    public static List<String> splitToElements(String path) {
        if (path != null) {
            String[] cleanPathParts = path.trim().split(PATH_SEPARATOR);

            List<String> retValue = new ArrayList<>(cleanPathParts.length);

            for (String pathPart : cleanPathParts) {
                if (StringUtils.hasText(pathPart)) {
                    retValue.add(pathPart.trim());
                }
            }

            if (retValue.size() > 0) {
                return retValue;
            }
        }

        return null;
    }
}

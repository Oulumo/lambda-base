package fi.oulumo.lambda.core.util;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class StringUtils {
    private StringUtils() {
        // Utility class, no instances are allowed.
    }

    /**
     * Checks that the text is not null and it has text other than just whitespaces.
     */
    public static boolean hasText(String text) {
        return ((text != null) && (!text.trim().equals("")));
    }

    /**
     * Checks if all input values are conforming to the rules in {@link #hasText(String)}.
     */
    public static boolean allHasText(String... texts) {
        for (String text : texts) {
            if (!hasText(text)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Converts the given text into snake case (e.g.: "ExampleText" becomes "example_text"). Whitespace characters around
     * the text will be trimmed, inside they will be converted to "_" (but there will be no "__" like structures).
     */
    public static String toSnakeCase(String text) {
        if (hasText(text)) {
            String cleanText = text.trim();
            StringBuilder retValue = new StringBuilder(cleanText.length() + 5);

            boolean lastWasWhitespace = false;

            for (int i=0; i < cleanText.length(); i++) {
                char c = cleanText.charAt(i);

                if (Character.isWhitespace(c)) {
                    if (!lastWasWhitespace) {
                        retValue.append("_");
                        lastWasWhitespace = true;
                    }
                }
                else if (Character.isUpperCase(c)) {
                    if ((i > 0) && (!lastWasWhitespace)) {
                        retValue.append("_");
                    }
                    retValue.append(Character.toLowerCase(c));
                    lastWasWhitespace = false;
                }
                else {
                    retValue.append(c);
                    lastWasWhitespace = false;
                }
            }

            return retValue.toString();
        }

        return null;
    }
}

package fi.oulumo.lambda.core.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class StringUtilsTest {
    @Test
    public void allHasText_True() throws Exception {
        assertTrue(StringUtils.allHasText("one", "two", "three"));
    }

    @Test
    public void allHasText_False() throws Exception {
        assertFalse(StringUtils.allHasText("one", "two", "three", " \t"));
    }

    @org.junit.Test
    public void hasText_TrueOnText() throws Exception {
        assertTrue(StringUtils.hasText("Sometext"));
    }

    @org.junit.Test
    public void hasText_FalseOnNull() throws Exception {
        assertFalse(StringUtils.hasText(null));
    }

    @org.junit.Test
    public void hasText_FalseOnEmpty() throws Exception {
        assertFalse(StringUtils.hasText(""));
    }

    @org.junit.Test
    public void hasText_FalseOnWhitespaces() throws Exception {
        assertFalse(StringUtils.hasText(" \t"));
    }

    @org.junit.Test
    public void toSnakeCase_Normal() throws Exception {
        assertEquals("this_is_sparta", StringUtils.toSnakeCase("ThisIsSparta"));
    }

    @org.junit.Test
    public void toSnakeCase_WithSpace() throws Exception {
        assertEquals("this_is_sparta", StringUtils.toSnakeCase("This is sparta"));
    }

    @org.junit.Test
    public void toSnakeCase_MixedWithSpace() throws Exception {
        assertEquals("this_is_sparta", StringUtils.toSnakeCase("This Is Sparta"));
    }

    @org.junit.Test
    public void toSnakeCase_WhiteSpaceOnly() throws Exception {
        assertNull(StringUtils.toSnakeCase(" \t"));
    }

    @org.junit.Test
    public void toSnakeCase_Null() throws Exception {
        assertNull(StringUtils.toSnakeCase(null));
    }

    @org.junit.Test
    public void toSnakeCase_Empty() throws Exception {
        assertNull(StringUtils.toSnakeCase(""));
    }
}
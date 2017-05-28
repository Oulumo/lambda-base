package fi.oulumo.lambda.dynamodb.util;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class PrefixUtilsTest {
    @Test
    public void dynamoPrefix_simple() throws Exception {
        assertThat(PrefixUtils.getDynamoDbTablePrefixFrom("trial"), is("TRIAL-"));
    }

    @Test
    public void dynamoPrefix_null() throws Exception {
        assertThat(PrefixUtils.getDynamoDbTablePrefixFrom(null), is("DEV-"));
    }

    @Test
    public void dynamoPrefix_empty() throws Exception {
        assertThat(PrefixUtils.getDynamoDbTablePrefixFrom(""), is("DEV-"));
    }

    @Test
    public void dynamoPrefix_whitespace() throws Exception {
        assertThat(PrefixUtils.getDynamoDbTablePrefixFrom("  "), is("DEV-"));
    }
}
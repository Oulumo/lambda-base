package fi.oulumo.lambda.s3.util;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class PrefixUtilsTest {
    @Test
    public void s3Prefix_simple() throws Exception {
        assertThat(PrefixUtils.getPrefixedS3BucketName("trial", "some"), is("trial-some"));
    }

    @Test
    public void s3Prefix_nullStage() throws Exception {
        assertThat(PrefixUtils.getPrefixedS3BucketName(null, "some"), is("dev-some"));
    }

    @Test
    public void s3Prefix_emptyStage() throws Exception {
        assertThat(PrefixUtils.getPrefixedS3BucketName("", "some"), is("dev-some"));
    }

    @Test
    public void s3Prefix_whitespaceStage() throws Exception {
        assertThat(PrefixUtils.getPrefixedS3BucketName("  ", "some"), is("dev-some"));
    }
}
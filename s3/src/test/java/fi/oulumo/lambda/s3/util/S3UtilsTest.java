package fi.oulumo.lambda.s3.util;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class S3UtilsTest {
    @Test
    public void getPublicAccessUrl_simple() throws Exception {
        assertThat(S3Utils.getPublicAccessUrl("some", "other"), is(equalTo("https://some.s3.amazonaws.com/other")));
    }

    @Test
    public void getPublicAccessUrl_simple2() throws Exception {
        assertThat(S3Utils.getPublicAccessUrl("some", "other/image.png"), is(equalTo("https://some.s3.amazonaws.com/other/image.png")));
    }

    @Test
    public void getPublicAccessUrl_nullBucket() throws Exception {
        assertThat(S3Utils.getPublicAccessUrl(null, "other"), is(nullValue()));
    }

    @Test
    public void getPublicAccessUrl_emptyBucket() throws Exception {
        assertThat(S3Utils.getPublicAccessUrl(" ", "other"), is(nullValue()));
    }

    @Test
    public void getPublicAccessUrl_nullObjectKey() throws Exception {
        assertThat(S3Utils.getPublicAccessUrl("some", null), is(nullValue()));
    }

    @Test
    public void getPublicAccessUrl_emptyObjectKey() throws Exception {
        assertThat(S3Utils.getPublicAccessUrl("some", " "), is(nullValue()));
    }
}
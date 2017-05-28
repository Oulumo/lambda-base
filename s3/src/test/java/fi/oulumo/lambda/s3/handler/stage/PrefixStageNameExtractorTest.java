package fi.oulumo.lambda.s3.handler.stage;

import fi.oulumo.lambda.core.util.StageUtils;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class PrefixStageNameExtractorTest {
    @Test
    public void extract_simple() throws Exception {
        assertThat(PrefixStageNameExtractor.extractStageNameFromBucketName("trial-some"), is("trial"));
    }

    @Test
    public void extract_simple3() throws Exception {
        assertThat(PrefixStageNameExtractor.extractStageNameFromBucketName("TrIaL-some"), is("trial"));
    }

    @Test
    public void extract_simple4() throws Exception {
        assertThat(PrefixStageNameExtractor.extractStageNameFromBucketName("something"), is(StageUtils.DEVELOPMENT_STAGE));
    }

    @Test
    public void extract_empty() throws Exception {
        assertThat(PrefixStageNameExtractor.extractStageNameFromBucketName(""), is(StageUtils.DEVELOPMENT_STAGE));
    }

    @Test
    public void extract_whitespace() throws Exception {
        assertThat(PrefixStageNameExtractor.extractStageNameFromBucketName("  "), is(StageUtils.DEVELOPMENT_STAGE));
    }

    @Test
    public void extract_null() throws Exception {
        assertThat(PrefixStageNameExtractor.extractStageNameFromBucketName(null), is(StageUtils.DEVELOPMENT_STAGE));
    }
}
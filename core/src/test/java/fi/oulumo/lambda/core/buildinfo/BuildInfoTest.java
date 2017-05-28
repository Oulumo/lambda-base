package fi.oulumo.lambda.core.buildinfo;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class BuildInfoTest {
    @Test
    public void testLambdaName() {
        assertThat(BuildInfo.getLambdaName(), is(equalTo("Test")));
    }

    @Test
    public void testBuildVersion() {
        assertThat(BuildInfo.getBuildVersion(), is(equalTo("1.0.TEST")));
    }

    @Test
    public void testBuildDate() {
        assertThat(BuildInfo.getBuildDate(), is(equalTo("17:00 May 28, 2017")));
    }

    @Test
    public void testBuildUser() {
        assertThat(BuildInfo.getBuildUser(), is(equalTo("testuser")));
    }

    @Test
    public void testInfoString() {
        assertThat(BuildInfo.getInfoString(), is(equalTo("Test lambda v1.0.TEST (built on: 17:00 May 28, 2017 by testuser)")));
    }
}

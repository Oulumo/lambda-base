package fi.oulumo.lambda.router.route;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class HttpMethodTest {
    @Test
    public void getFromString() throws Exception {
        String get = HttpMethod.Get.toString();

        assertThat(HttpMethod.getFromString(get), is(equalTo(HttpMethod.Get)));
    }

    @Test
    public void getFromString_freeForm() throws Exception {
        assertThat(HttpMethod.getFromString("gEt"), is(equalTo(HttpMethod.Get)));
    }
}
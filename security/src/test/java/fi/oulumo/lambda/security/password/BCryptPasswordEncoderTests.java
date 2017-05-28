/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fi.oulumo.lambda.security.password;

import org.junit.Test;

import java.security.SecureRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Dave Syer
 *
 */
public class BCryptPasswordEncoderTests {

    @Test
    public void matches() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("password");
        assertThat(result.equals("password"), is(false));
        assertThat(encoder.matches("password", result), is(true));
    }

    @Test
    public void unicode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("passw\u9292rd");
        assertThat(encoder.matches("pass\u9292\u9292rd", result), is(false));
        assertThat(encoder.matches("passw\u9292rd", result), is(true));
    }

    @Test
    public void notMatches() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("password");
        assertThat(encoder.matches("bogus", result), is(false));
    }

    @Test
    public void customStrength() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        String result = encoder.encode("password");
        assertThat(encoder.matches("password", result), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void badLowCustomStrength() {
        new BCryptPasswordEncoder(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badHighCustomStrength() {
        new BCryptPasswordEncoder(32);
    }

    @Test
    public void customRandom() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8, new SecureRandom());
        String result = encoder.encode("password");
        assertThat(encoder.matches("password", result), is(true));
    }

    @Test
    public void doesntMatchNullEncodedValue() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertThat(encoder.matches("password", null), is(false));
    }

    @Test
    public void doesntMatchEmptyEncodedValue() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertThat(encoder.matches("password", ""), is(false));
    }

    @Test
    public void doesntMatchBogusEncodedValue() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertThat(encoder.matches("password", "012345678901234567890123456789"), is(false));
    }
}

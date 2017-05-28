package fi.oulumo.lambda.security.dagger;

import dagger.Module;
import dagger.Provides;
import fi.oulumo.lambda.security.password.BCryptPasswordEncoder;

import javax.inject.Singleton;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Module
public class SecurityModule {
    @Provides
    @Singleton
    public BCryptPasswordEncoder providePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

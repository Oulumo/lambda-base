package fi.oulumo.lambda.core.dagger;

import dagger.Module;
import dagger.Provides;
import fi.oulumo.lambda.core.context.IHandlerContextDecorator;
import fi.oulumo.lambda.core.context.NoOpHandlerContextDecorator;

import javax.inject.Singleton;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Module
public class HandlerContextDecoratorModule {
    @Provides
    @Singleton
    public IHandlerContextDecorator provideHandlerContextDecorator() {
        return new NoOpHandlerContextDecorator();
    }
}

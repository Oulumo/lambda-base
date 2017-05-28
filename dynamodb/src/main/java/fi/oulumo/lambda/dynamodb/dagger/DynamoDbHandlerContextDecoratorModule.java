package fi.oulumo.lambda.dynamodb.dagger;

import fi.oulumo.lambda.core.context.IHandlerContextDecorator;
import fi.oulumo.lambda.core.dagger.HandlerContextDecoratorModule;
import fi.oulumo.lambda.dynamodb.DynamoDbHandlerContextDecorator;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class DynamoDbHandlerContextDecoratorModule extends HandlerContextDecoratorModule {
    @Override
    public IHandlerContextDecorator provideHandlerContextDecorator() {
        return new DynamoDbHandlerContextDecorator();
    }
}

package fi.oulumo.lambda.example.test;

import com.google.gson.Gson;
import dagger.Component;
import fi.oulumo.lambda.apigateway.dagger.ApiGatewayModule;
import fi.oulumo.lambda.core.dagger.HandlerContextDecoratorModule;
import fi.oulumo.lambda.example.dagger.ExampleRequestManagerProvider;
import fi.oulumo.lambda.tester.apigateway.RequestTestHelper;

import javax.inject.Singleton;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Component(modules = {ApiGatewayModule.class, HandlerContextDecoratorModule.class})
@Singleton
public interface TestExampleRequestManagerProvider extends ExampleRequestManagerProvider {
    Gson gson();

    RequestTestHelper testHelper();
}

package fi.oulumo.lambda.example.dagger;

import dagger.Component;
import fi.oulumo.lambda.apigateway.dagger.ApiGatewayModule;
import fi.oulumo.lambda.core.dagger.HandlerContextDecoratorModule;
import fi.oulumo.lambda.example.ExampleRequestManager;
import fi.oulumo.lambda.example.ExampleRequestManagerWithCors;

import javax.inject.Singleton;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Component(modules = {ApiGatewayModule.class, HandlerContextDecoratorModule.class})
@Singleton
public interface ExampleRequestManagerProvider {
    ExampleRequestManager manager();

    ExampleRequestManagerWithCors managerWithCors();
}

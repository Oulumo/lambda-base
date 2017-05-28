package fi.oulumo.lambda.apigateway;

import com.google.gson.Gson;
import dagger.Component;
import fi.oulumo.lambda.apigateway.dagger.ApiGatewayModule;

import javax.inject.Singleton;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Component(modules = {ApiGatewayModule.class})
@Singleton
public interface TestProvider {
    Gson gson();
}

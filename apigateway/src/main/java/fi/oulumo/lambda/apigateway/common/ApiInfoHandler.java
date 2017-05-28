package fi.oulumo.lambda.apigateway.common;

import fi.oulumo.lambda.apigateway.common.dto.ApiInfo;
import fi.oulumo.lambda.apigateway.handler.AbstractLambdaRequestHandler;
import fi.oulumo.lambda.apigateway.handler.HandlerResponse;

import javax.inject.Inject;


/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class ApiInfoHandler extends AbstractLambdaRequestHandler<Void, ApiInfo> {
    @Inject
    ApiInfo apiInfo;

    @Inject
    public ApiInfoHandler() {
    }

    @Override
    protected HandlerResponse<ApiInfo> doHandleLambdaRequest(Void input) {
        return new HandlerResponse<>(apiInfo);
    }

    @Override
    public Class<Void> getInputClass() {
        return null;
    }
}
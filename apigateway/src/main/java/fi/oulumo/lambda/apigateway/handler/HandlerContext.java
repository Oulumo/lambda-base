package fi.oulumo.lambda.apigateway.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequest;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequestContext;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequestContextAuthorizer;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequestContextIdentity;
import fi.oulumo.lambda.core.context.AbstractHandlerContext;
import fi.oulumo.lambda.core.util.StageUtils;
import fi.oulumo.lambda.core.util.StringUtils;

import java.util.Map;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class HandlerContext extends AbstractHandlerContext {
    private LambdaRequest lambdaRequest;

    private Gson gson;

    public HandlerContext(Context awsContext, LambdaRequest lambdaRequest, Gson gson) {
        super(awsContext);
        this.lambdaRequest = lambdaRequest;
        this.gson = gson;
    }


    @Override
    public LambdaLogger getLambdaLogger() {
        return getAwsContext().getLogger();
    }

    @Override
    public String getStageName() {
        if (getLambdaRequestContext() != null) {
            return StageUtils.getCanonicalStageNameFrom(getLambdaRequestContext().getStage());
        }

        return StageUtils.getCanonicalStageNameFrom(null);
    }

    @Override
    public boolean isProductionStage() {
        if (getLambdaRequestContext() != null) {
            return StageUtils.isProductionStage(getLambdaRequestContext().getStage());
        }

        return false;
    }

    public LambdaRequest getLambdaRequest() {
        return lambdaRequest;
    }

    public LambdaRequestContext getLambdaRequestContext() {
        return lambdaRequest.getRequestContext();
    }

    public String getParameterValue(String name) {
        String retValue = getValueFromMap(lambdaRequest.getPathParameters(), name);

        if (retValue == null) {
            retValue = getValueFromMap(lambdaRequest.getQueryStringParameters(), name);
        }

        return retValue;
    }

    public String getHeaderValue(String name) {
        return getValueFromMap(lambdaRequest.getHeaders(), name);
    }

    public String getStageVariableValue(String name) {
        return getValueFromMap(lambdaRequest.getStageVariables(), name);
    }

    public String getAuthorizedPrincipalId() {
        if (getLambdaRequestContext() != null) {
            LambdaRequestContextAuthorizer authorizer = getLambdaRequestContext().getAuthorizer();

            if (authorizer != null) {
                return authorizer.getPrincipalId();
            }
        }

        return null;
    }

    public LambdaRequestContextIdentity getIdentity() {
        if (getLambdaRequestContext() != null) {
            return getLambdaRequestContext().getIdentity();
        }

        return null;
    }

    public Gson getGson() {
        return gson;
    }

    private String getValueFromMap(Map<String, String> map, String key) {
        if (map != null) {
            String value = map.get(key);

            if (StringUtils.hasText(value)) {
                return value.trim();
            }
        }

        return null;
    }
}

package fi.oulumo.lambda.authenticator.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import fi.oulumo.lambda.core.context.AbstractHandlerContext;
import fi.oulumo.lambda.core.util.StageUtils;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class HandlerContext extends AbstractHandlerContext {
    private String stage;

    public HandlerContext(Context awsContext, String stage) {
        super(awsContext);
        this.stage = stage;
    }

    @Override
    public LambdaLogger getLambdaLogger() {
        return getAwsContext().getLogger();
    }

    @Override
    public boolean isProductionStage() {
        return StageUtils.isProductionStage(stage);
    }

    @Override
    public String getStageName() {
        return stage;
    }
}

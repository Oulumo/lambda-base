package fi.oulumo.lambda.core.context;

import com.amazonaws.services.lambda.runtime.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public abstract class AbstractHandlerContext implements IHandlerContext {
    private Context awsContext;
    private Map<Class, Object> decorations;

    public AbstractHandlerContext(Context awsContext) {
        this.awsContext = awsContext;
        this.decorations = new HashMap<>();
    }

    @Override
    public Context getAwsContext() {
        return awsContext;
    }

    @Override
    public <P> void registerDecoration(Class<P> decorationClass, P decoration) {
        if (decoration != null) {
            decorations.put(decorationClass, decoration);
        }
    }

    @Override
    public <P> P getDecoration(Class<P> decorationClass) {
        if (decorationClass != null) {
            Object retValue = decorations.get(decorationClass);
            if (retValue != null) {
                return decorationClass.cast(retValue);
            }
        }

        return null;
    }

    @Override
    public String getRequestId() {
        return awsContext.getAwsRequestId();
    }
}

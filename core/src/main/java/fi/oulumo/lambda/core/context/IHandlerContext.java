package fi.oulumo.lambda.core.context;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

/**
 * The context for the handlers, to access some global information (e.g.: AWS context, DynamoDB mapper config, etc.).
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public interface IHandlerContext {
    /**
     * Returns the underlying AWS context.
     */
    Context getAwsContext();

    /**
     * Returns the raw lambda logger.
     */
    LambdaLogger getLambdaLogger();

    /**
     * If true, the lambda is called in a production context, false otherwise.
     */
    boolean isProductionStage();

    /**
     * Returns the canonical stage name the lambda runs in (all lowercase).
     */
    String getStageName();

    /**
     * Returns the current request's id.
     */
    String getRequestId();

    /**
     * Register a decoration object (object that needs to be accessed within the lambda request chain).
     *
     * @param decorationClass The class of the decoration object (used for lookup - see bellow).
     * @param decoration The decoration itself (can't be null).
     */
    <P> void registerDecoration(Class<P> decorationClass, P decoration);

    /**
     * Returns the matching decoration, if any. Might return null.
     *
     * @param decorationClass The class of the decoration object.
     *
     * @return The decoration object if found, null otherwise.
     */
    <P> P getDecoration(Class<P> decorationClass);
}

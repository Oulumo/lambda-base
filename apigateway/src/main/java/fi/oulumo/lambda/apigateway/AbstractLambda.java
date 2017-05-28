package fi.oulumo.lambda.apigateway;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Helper class to implement {@link RequestStreamHandler}'s that are using Dagger IoC.
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public abstract class AbstractLambda<L extends AbstractLambdaRequestManager> implements RequestStreamHandler {
    private L requestManager;

    public AbstractLambda() {
        requestManager = createRequestManager();
    }

    protected abstract L createRequestManager();

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        requestManager.handleRequest(input, output, context);
    }
}

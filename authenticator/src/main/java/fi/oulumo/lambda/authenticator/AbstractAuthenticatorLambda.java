package fi.oulumo.lambda.authenticator;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import fi.oulumo.lambda.authenticator.dto.AuthenticationRequest;
import fi.oulumo.lambda.authenticator.dto.AuthenticationResponse;
import fi.oulumo.lambda.authenticator.handler.AbstractAuthenticationHandler;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public abstract class AbstractAuthenticatorLambda<L extends AbstractAuthenticationHandler> implements RequestHandler<AuthenticationRequest, AuthenticationResponse> {
    private L authenticationHandler;

    public AbstractAuthenticatorLambda() {
        authenticationHandler = createAuthenticationHandler();
    }

    protected abstract L createAuthenticationHandler();

    @Override
    public AuthenticationResponse handleRequest(AuthenticationRequest input, Context context) {
        return authenticationHandler.handleRequest(input, context);
    }
}


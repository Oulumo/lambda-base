package fi.oulumo.lambda.apigateway.exception;

import fi.oulumo.lambda.apigateway.handler.HandlerResponse;

/**
 * Translates Exceptions to {@link HandlerResponse} instances.
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public interface IErrorTranslator {
    HandlerResponse<ErrorObject> translateError(Throwable t);
}

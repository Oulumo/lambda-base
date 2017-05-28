package fi.oulumo.lambda.apigateway;


import fi.oulumo.lambda.apigateway.dto.request.LambdaRequest;
import fi.oulumo.lambda.apigateway.dto.response.LambdaResponse;

/**
 * Implementing classes can further transform, Lambda responses - for example
 * by adding CORS headers.
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public interface ILamdaResponseTransformer {
    LambdaResponse transformResponse(LambdaRequest request, LambdaResponse response, boolean errorResponse);
}

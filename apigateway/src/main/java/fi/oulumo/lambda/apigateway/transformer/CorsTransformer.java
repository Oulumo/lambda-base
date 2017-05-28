package fi.oulumo.lambda.apigateway.transformer;

import fi.oulumo.lambda.apigateway.ILamdaResponseTransformer;
import fi.oulumo.lambda.apigateway.dto.HttpHeader;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequest;
import fi.oulumo.lambda.apigateway.dto.response.LambdaResponse;
import fi.oulumo.lambda.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple response transformer that adds CORS Allow-Origin and (optionally) Expose-Headers
 * headers. For it to do any change, the request MUST have 'Origin' header specified.
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class CorsTransformer implements ILamdaResponseTransformer {
    private String exposedHeaders;

    public CorsTransformer() {
        this(HttpHeader.Location.getHttpName());
    }

    public CorsTransformer(String exposedHeaders) {
        this.exposedHeaders = exposedHeaders;
    }

    @Override
    public LambdaResponse transformResponse(LambdaRequest request, LambdaResponse response, boolean errorResponse) {
        String requestOriginValue = request.getHeaderValue(HttpHeader.Origin);

        if (StringUtils.hasText(requestOriginValue)) {
            Map<String, String> headers = response.getHeaders();
            if (headers == null) {
                headers = new HashMap<>();
                response.setHeaders(headers);
            }

            headers.put(HttpHeader.CorsAllowOrigin.getHttpName(), requestOriginValue.trim());
            if (StringUtils.hasText(exposedHeaders)) {
                headers.put(HttpHeader.CorsExposeHeaders.getHttpName(), exposedHeaders);
            }
        }

        return response;
    }
}

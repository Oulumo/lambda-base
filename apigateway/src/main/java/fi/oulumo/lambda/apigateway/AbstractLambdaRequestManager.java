package fi.oulumo.lambda.apigateway;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequest;
import fi.oulumo.lambda.apigateway.dto.request.LambdaRequestContext;
import fi.oulumo.lambda.apigateway.dto.response.HttpStatus;
import fi.oulumo.lambda.apigateway.dto.response.LambdaResponse;
import fi.oulumo.lambda.apigateway.exception.ErrorObject;
import fi.oulumo.lambda.apigateway.exception.IErrorTranslator;
import fi.oulumo.lambda.apigateway.exception.LambdaException;
import fi.oulumo.lambda.apigateway.handler.HandlerContext;
import fi.oulumo.lambda.apigateway.handler.HandlerResponse;
import fi.oulumo.lambda.apigateway.handler.ILambdaRequestHandler;
import fi.oulumo.lambda.core.buildinfo.BuildInfo;
import fi.oulumo.lambda.core.context.HandlerContextHolder;
import fi.oulumo.lambda.core.context.IHandlerContextDecorator;
import fi.oulumo.lambda.core.util.StringUtils;
import fi.oulumo.lambda.log.ILogger;
import fi.oulumo.lambda.log.LoggerFactory;
import fi.oulumo.lambda.router.Router;
import fi.oulumo.lambda.router.RouterMatchResult;
import fi.oulumo.lambda.router.route.HttpMethod;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class AbstractLambdaRequestManager<R> {
    public static final ILogger log = LoggerFactory.createLogger(AbstractLambdaRequestManager.class);
    public static final String PATH_PARAMETER_PROXY = "proxy";

    private final Gson gson;
    private final IErrorTranslator errorTranslator;
    private final IHandlerContextDecorator handlerContextDecorator;
    private ILamdaResponseTransformer responseTransformer;

    private Router<R,ILambdaRequestHandler> router;

    public AbstractLambdaRequestManager(Gson gson, IErrorTranslator errorTranslator,
                                        IHandlerContextDecorator handlerContextDecorator) {
        this.gson = gson;
        this.errorTranslator = errorTranslator;
        this.handlerContextDecorator = handlerContextDecorator;
    }

    public AbstractLambdaRequestManager(Gson gson, IErrorTranslator errorTranslator,
                                        IHandlerContextDecorator handlerContextDecorator,
                                        ILamdaResponseTransformer responseTransformer) {
        this.gson = gson;
        this.errorTranslator = errorTranslator;
        this.handlerContextDecorator = handlerContextDecorator;
        setResponseTransformer(responseTransformer);
    }

    /**
     * Returns the response transformer that will be invoked after handler invocations (both successful and
     * erroneous).
     */
    public ILamdaResponseTransformer getResponseTransformer() {
        return responseTransformer;
    }

    /**
     * Sets the response transformer that will be invoked after handler invocations (both successful and
     * erroneous).
     */
    public void setResponseTransformer(ILamdaResponseTransformer responseTransformer) {
        this.responseTransformer = responseTransformer;
    }

    /**
     * Subclasses must implement this method to setup the router that will be used to find the matching
     * {@link ILambdaRequestHandler} instance, that in turn will actually handle the request.
     */
    protected abstract Router<R,ILambdaRequestHandler> setupRouter();

    /**
     * Subclasses can override this method to switch from proxy-path based routing (default) to
     * full path based routing.
     */
    protected RoutingMethod getRoutingMethod() {
        return RoutingMethod.ProxyPath;
    }

    @SuppressWarnings("unchecked")
    public void handleRequest(InputStream request, OutputStream response, Context context) throws IOException {
        LambdaRequest lambdaRequest = null;
        HandlerResponse handlerResponse;

        try {
            String requestString = IOUtils.toString(request, Charset.defaultCharset());
            lambdaRequest = gson.fromJson(requestString, LambdaRequest.class);

            HandlerContext handlerContext = new HandlerContext(context, lambdaRequest, gson);
            if (handlerContextDecorator != null) {
                handlerContextDecorator.decorateContext(handlerContext);
            }
            HandlerContextHolder.set(handlerContext);

            log.info(BuildInfo.getInfoString());

            LambdaRequestContext lambdaRequestContext = handlerContext.getLambdaRequestContext();
            if (lambdaRequestContext != null) {

                log.info("API Gateway Request Id: %s", lambdaRequestContext.getRequestId());
                if (lambdaRequestContext.getIdentity() != null) {
                    log.info("API call from: %s with user-agent: %s",
                            lambdaRequestContext.getIdentity().getSourceIp(),
                            lambdaRequestContext.getIdentity().getUserAgent()
                    );
                }
            }

            if (router == null) {
                log.info("Router is not yet initialized, doing it now...");
                router = setupRouter();
            }

            handlerResponse = doRouteRequest(handlerContext);

            writeResponse(response, lambdaRequest, handlerResponse, false);
        }
        catch (Throwable t) {
            handlerResponse = errorTranslator.translateError(t);

            if (HttpStatus.InternalError.equals(handlerResponse.getStatus())) {
                log.error(t, "Internal error.");
            }
            else {
                log.error(((ErrorObject)handlerResponse.getValue()).getErrorMessage());
            }

            writeResponse(response, lambdaRequest, handlerResponse, true);
        }
        finally {
            HandlerContextHolder.remove();
        }
    }

    @SuppressWarnings("unchecked")
    protected void writeResponse(OutputStream response, LambdaRequest lambdaRequest, HandlerResponse handlerResponse, boolean errorResponse) throws IOException {
        Object handlerResponseValue = handlerResponse.getValue();
        LambdaResponse returnValue = new LambdaResponse(
                handlerResponse.getStatus(),
                handlerResponse.getHeaders(),
                handlerResponseValue == null ? null : gson.toJson(handlerResponseValue)
        );

        if (responseTransformer != null) {
            returnValue = responseTransformer.transformResponse(lambdaRequest, returnValue, errorResponse);
        }

        log.info("Returning HTTP status %1$s (%2$d)", returnValue.getStatus().name(), returnValue.getStatus().getCode());

        String returnValueString = gson.toJson(returnValue);

        IOUtils.write(returnValueString, response, Charset.defaultCharset());
    }

    protected HandlerResponse doRouteRequest(HandlerContext handlerContext) {
        LambdaRequest lambdaRequest = handlerContext.getLambdaRequest();

        String requestPath = (getRoutingMethod().equals(RoutingMethod.ProxyPath)) ?
                             lambdaRequest.getPathParameters().get(PATH_PARAMETER_PROXY) :
                             lambdaRequest.getPath();

        HttpMethod httpMethod = HttpMethod.getFromString(lambdaRequest.getHttpMethod());

        if ((StringUtils.hasText(requestPath)) && (httpMethod != null)) {
            RouterMatchResult<ILambdaRequestHandler> matchResult = router.findHandlerFor(httpMethod, requestPath);

            if (matchResult != null) {
                Optional<Map<String, String>> matchResultPathParameters = matchResult.getPathParameters();

                if (matchResultPathParameters.isPresent()) {
                    lambdaRequest.addPathParameters(matchResultPathParameters.get());
                }

                ILambdaRequestHandler handler = matchResult.getHandler();
                log.info("Routing request to handler: %1$s", handler.getClass().getSimpleName());

                return handler.handleLambdaRequest(lambdaRequest.getRequestBody(), gson);
            }
            else {
                throw new LambdaException(HttpStatus.BadRequest, "Unknown path selected");
            }
        }
        else {
            throw new LambdaException(HttpStatus.BadRequest, "HTTP method / request path is not defined.");
        }
    }
}

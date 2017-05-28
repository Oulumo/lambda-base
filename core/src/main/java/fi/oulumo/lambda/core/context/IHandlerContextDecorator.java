package fi.oulumo.lambda.core.context;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public interface IHandlerContextDecorator {
    void decorateContext(IHandlerContext context);
}

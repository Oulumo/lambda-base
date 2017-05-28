package fi.oulumo.lambda.core.context;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class NoOpHandlerContextDecorator implements IHandlerContextDecorator {
    @Override
    public void decorateContext(IHandlerContext context) {
        // Do nothing
    }
}

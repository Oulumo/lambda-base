package fi.oulumo.lambda.core.context;

/**
 * Holds the current {@link IHandlerContext} in an {@link InheritableThreadLocal} static field.
 *
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class HandlerContextHolder {
    private static final ThreadLocal<IHandlerContext> CONTEXT_THREAD_LOCAL = new InheritableThreadLocal<IHandlerContext>() {
        @Override
        protected IHandlerContext initialValue() {
            return null;
        }
    };

    public static IHandlerContext get() {
        return CONTEXT_THREAD_LOCAL.get();
    }

    public static void remove() {
        CONTEXT_THREAD_LOCAL.remove();
    }

    public static void set(IHandlerContext value) {
        CONTEXT_THREAD_LOCAL.set(value);
    }
}

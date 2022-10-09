package dynamicproxy.measure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TimeMeasuringProxyHandler implements InvocationHandler {
    private final Object originalObject;

    public TimeMeasuringProxyHandler(Object originalObject) {
        this.originalObject = originalObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Measuring Proxy - Before executing method : " + method.getName());

        Object result;
        long startTime = System.currentTimeMillis();
        try {
            result = method.invoke(originalObject, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
        long endTime = System.currentTimeMillis();

        System.out.printf("Measuring Proxy - Execution of %s() took %dms\n", method.getName(), endTime - startTime);
        return result;
    }
}

package dynamicproxy.cache.proxy;

import java.lang.reflect.Proxy;

public class ProxyFactory {
    @SuppressWarnings("unchecked")
    public static <T> T createCachingProxy(Object originalObject) {
        Class<?>[] interfaces = originalObject.getClass().getInterfaces();

        CachingInvocationHandler cachingInvocationHandler = new CachingInvocationHandler(originalObject);

        return (T) Proxy.newProxyInstance(
                originalObject.getClass().getClassLoader(),
                interfaces,
                cachingInvocationHandler);
    }
}

package dynamicproxy.cache;

import dynamicproxy.cache.proxy.ProxyFactory;

public class Main {
    public static void main(String[] args) {
//        NumberGenerator generator = new NumberGeneratorImpl();
        NumberGenerator generator = ProxyFactory.createCachingProxy(new NumberGeneratorImpl());
        for (int i = 0; i < 10; i++) {
            int seed = i % 5;
            System.out.println("Seed " + seed + " : " + generator.generate(seed));
        }
    }
}

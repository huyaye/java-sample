package dynamicproxy.cache;

import dynamicproxy.cache.annotation.Cacheable;

public class NumberGeneratorImpl implements NumberGenerator {
    @Override
    @Cacheable
    public int generate(int seed) {
        return (int)(Math.random() * 100);
    }
}

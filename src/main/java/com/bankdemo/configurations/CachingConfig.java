package com.bankdemo.configurations;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ilyas.Kuanyshbekov on 16.09.2016.
 */
@Configuration
@EnableCaching
public class CachingConfig implements CachingConfigurer {

    final static String CACHE_POLICY = "LRU";

    @Bean(destroyMethod = "shutdown")
    public net.sf.ehcache.CacheManager ehCacheManager() {
        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
        configuration.addCache(cacheConfig("ehCache", 1000));
        return net.sf.ehcache.CacheManager.newInstance(configuration);
    }


    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }

    private CacheConfiguration cacheConfig(String name, long maxEntries) {
        CacheConfiguration config = new CacheConfiguration();
        config.setName(name);
        config.setMaxEntriesLocalHeap(maxEntries);
        config.setMemoryStoreEvictionPolicy(CACHE_POLICY);
        return config;
    }


}

package com.yizhilu.os.common.util;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.Configuration;

import org.apache.log4j.Logger;

/**
 * @author : qinggang.liu voo@163.com
 * @ClassName com.yizhilu.common.util.CacheKit
 * @description
 * @Create Date : 2014-9-4 上午9:40:19
 */
public class CacheKit {


    private static volatile CacheManager cacheManager;
    private static Logger log = Logger.getLogger(CacheKit.class);

    private static CacheKit cacheKit;

    private String configurationFileName;
    private URL configurationFileURL;
    private InputStream inputStream;
    private Configuration configuration;

    /**
     *
     */
    public static CacheKit getInstance() {
        if (cacheKit == null) {
            cacheKit = new CacheKit();
        }
        return cacheKit;
    }

    public CacheKit() {
        createCacheManager();
        CacheKit.init(cacheManager);
    }

    public CacheKit(CacheManager cacheManager) {
        CacheKit.cacheManager = cacheManager;
    }

    public CacheKit(String configurationFileName) {
        this.configurationFileName = configurationFileName;
        createCacheManager();
    }

    public CacheKit(URL configurationFileURL) {
        this.configurationFileURL = configurationFileURL;
        createCacheManager();
    }

    public CacheKit(InputStream inputStream) {
        this.inputStream = inputStream;
        createCacheManager();
    }

    public CacheKit(Configuration configuration) {
        this.configuration = configuration;
        createCacheManager();
    }

    private void createCacheManager() {
        if (cacheManager != null)
            return;

        if (configurationFileName != null) {
            cacheManager = CacheManager.create(configurationFileName);
            return;
        }

        if (configurationFileURL != null) {
            cacheManager = CacheManager.create(configurationFileURL);
            return;
        }

        if (inputStream != null) {
            cacheManager = CacheManager.create(inputStream);
            return;
        }

        if (configuration != null) {
            cacheManager = CacheManager.create(configuration);
            return;
        }

        cacheManager = CacheManager.create();
    }

    public boolean stop() {
        cacheManager.shutdown();
        return true;
    }


    static void init(CacheManager cacheManager) {
        CacheKit.cacheManager = cacheManager;
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    static Cache getOrAddCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            synchronized (cacheManager) {
                cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    log.warn("Could not find cache config [" + cacheName + "], using default.");
                    cacheManager.addCacheIfAbsent(cacheName);
                    cache = cacheManager.getCache(cacheName);
                    log.debug("Cache [" + cacheName + "] started.");
                }
            }
        }
        return cache;
    }

    public void put(String cacheName, Object key, Object value) {
        getOrAddCache(cacheName).put(new Element(key, value));
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String cacheName, Object key) {
        Element element = getOrAddCache(cacheName).get(key);
        return element != null ? (T) element.getObjectValue() : null;
    }

    @SuppressWarnings("rawtypes")
    public List getKeys(String cacheName) {
        return getOrAddCache(cacheName).getKeys();
    }

    public void remove(String cacheName, Object key) {
        getOrAddCache(cacheName).remove(key);
    }

    public void removeAll(String cacheName) {
        getOrAddCache(cacheName).removeAll();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String cacheName, Object key, IDataLoader dataLoader) {
        Object data = get(cacheName, key);
        if (data == null) {
            data = dataLoader.load();
            put(cacheName, key, data);
        }
        return (T) data;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String cacheName, Object key, Class<? extends IDataLoader> dataLoaderClass) {
        Object data = get(cacheName, key);
        if (data == null) {
            try {
                IDataLoader dataLoader = dataLoaderClass.newInstance();
                data = dataLoader.load();
                put(cacheName, key, data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return (T) data;
    }

}

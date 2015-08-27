package mscache.core;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mscache.api.IMsCache;
import mscache.entity.CacheElement;

/**
 * @author tank
 * @email kaixiong.tan@qq.com
 * @date:2015年8月24日 下午5:24:44
 * @description:
 * @version :0.1
 */

public class MsCacheManager<T> implements IMsCache<T> {

	private Map<String, CacheElement<T>> cache = new ConcurrentHashMap<String, CacheElement<T>>();
	 
	/**
	 * 缓存超时时间，单位：毫秒
	 */
	private Long expired = 0L;

	public MsCacheManager() {
		this(5 * 1000 * 60L);
	}

	public MsCacheManager(Long expired) {
		this.expired = expired;
	}

	@Override
	public void refresh(String key, T target) {
		if (cache.containsKey(key)) {
			cache.remove(key);
		}
		cache.put(key, new CacheElement<T>(target));
	}

	@Override
	public T getCache(String key) {
		if (!this.exist(key)) {
			return null;
		}

		return cache.get(key).getData();
	}

	@Override
	public Boolean isExpired(String key) {
		if (!this.exist(key)) {
			return null;
		}

		long currtime = new Date().getTime();
		long lasttime = cache.get(key).getRefreshtime();

		return (currtime - lasttime) > expired;
	}

	@Override
	public void setExpired(Long millsec) {
		this.expired = millsec;
	}

	@Override
	public Boolean exist(String key) {
		return cache.containsKey(key);
	}

}

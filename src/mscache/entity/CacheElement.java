package mscache.entity;

import java.util.Date;

/**
 * @author tank
 * @email kaixiong.tan@qq.com
 * @date:2015年8月24日 下午5:21:45
 * @description:
 * @version :0.1
 */

public class CacheElement<T> {
	/**
	 * 上次缓存的数据
	 */
	private T data;

	/**
	 * 最后刷新时间
	 */
	private long refreshtime;

	public CacheElement(T data) {
		this(data, new Date().getTime());
	}

	public CacheElement(T data, long refreshtime) {
		this.data = data;
		this.refreshtime = refreshtime;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getRefreshtime() {
		return refreshtime;
	}

	public void setRefreshtime(long refreshtime) {
		this.refreshtime = refreshtime;
	}

}

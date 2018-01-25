package com.crm.manager.mail.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.InitializingBean;


/**  
 * 由spring管理的线程池类，返回的ExecutorService就是给我们来执行线程的  
 *如果不交给spring管理也是可以的，可以使用单例模式来实现同样功能，但是poolSize   *要hardcode了  
 */ 
public class EasyMailExecutorPool implements InitializingBean {
	
	// 线程池大小，spring配置文件中配置
	private int poolSize;
	private ExecutorService service;

	public ExecutorService getService() {
		return service;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}
	
	/**
	 * 在 bean 被初始化成功之后初始化线程池大小
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		service = Executors.newFixedThreadPool(poolSize);   

	}
}

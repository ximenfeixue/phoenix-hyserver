package com.ginkgocap.ywxt.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ThreadPoolUtils {

	static int CPU_CORE_SIZE = Runtime.getRuntime().availableProcessors();
	static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(CPU_CORE_SIZE * 3, CPU_CORE_SIZE * 20, 60L, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(100), new ThreadPoolExecutor.CallerRunsPolicy());

	static final ExecutorService EXECUTOR = new ThreadPoolExecutor(CPU_CORE_SIZE * 2, CPU_CORE_SIZE * 10, 60L, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(20), new ThreadPoolExecutor.CallerRunsPolicy());

	public static ExecutorService getExecutorService() {
		return EXECUTOR_SERVICE;
	}

	public static ExecutorService getExecutor() {
		return EXECUTOR;
	}

	private ThreadPoolUtils() {
	}

}

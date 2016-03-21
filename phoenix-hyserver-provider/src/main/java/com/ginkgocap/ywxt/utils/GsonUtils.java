package com.ginkgocap.ywxt.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonUtils {
	static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public static String objectToString(Object targetObj) {
		return GSON.toJson(targetObj);
	}

	public static <T> T StringToObject(Class<T> classOfT, String targetJson) {
		return GSON.fromJson(targetJson, classOfT);
	}

	private GsonUtils() {
	}
}

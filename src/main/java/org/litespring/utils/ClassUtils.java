package org.litespring.utils;

public class ClassUtils {

	public static ClassLoader getDefaultClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
}



package org.litespring.utils;

public class ClassUtils {

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		if (classLoader == null) {
			classLoader = ClassUtils.class.getClassLoader();

		}

		if (classLoader == null) {
			classLoader = ClassLoader.getSystemClassLoader();
		}

		return  classLoader;
	}
}



package org.litespring.beans.support;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.utils.ClassUtils;

/**
 *
 * 用于获取 Bean 的相关信息
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	
	public DefaultBeanFactory() {

	}

	@Override
	public BeanDefinition getBeanDefinition(String beanId) {
		return beanDefinitionMap.get(beanId);
	}

	@Override
	public void registerBeanDefinition(String beanId, BeanDefinition db) {
		beanDefinitionMap.put(beanId, db);
	}

	@Override
	public Object getBean(String beanId) {
		BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
		
		if (beanDefinition == null) {
			throw new BeanCreationException("Bean Definition does not exist");
		}
		
		String beanClassName = beanDefinition.getBeanClassName();
		ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
		try {
			Class<?> loadClass = defaultClassLoader.loadClass(beanClassName);
			return loadClass.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

}

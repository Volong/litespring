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
import org.litespring.utils.ClassUtils;

public class DefaultBeanFactory implements BeanFactory {

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	
	public DefaultBeanFactory(String configFile) {
		loadBeanDefinition(configFile);
	}

	private void loadBeanDefinition(String configFile) {
		
		try {
			ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
			InputStream is = defaultClassLoader.getResourceAsStream(configFile);
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(is);
			Element root = doc.getRootElement();
			
			Iterator<Element> elementIterator = root.elementIterator();
			
			while (elementIterator.hasNext()) {
				Element next = elementIterator.next();
				String id = next.attributeValue("id");
				String className = next.attributeValue("class");
				BeanDefinition bd = new GenericBeanDefinition(id, className);
				beanDefinitionMap.put(id, bd);
			}
			
		} catch (DocumentException e) {
			throw new BeanDefinitionStoreException("parsing xml document error", e);
		}
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanId) {
		return beanDefinitionMap.get(beanId);
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

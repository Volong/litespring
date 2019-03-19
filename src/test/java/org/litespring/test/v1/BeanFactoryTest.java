package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.core.io.ClasspathResource;
import org.litespring.service.v1.PetStoreService;



public class BeanFactoryTest {

	DefaultBeanFactory beanFactory;

	XmlBeanDefinitionReader reader;

	/**
	 * 每个测试用例运行之前都会运行这个方法
	 */
	@Before
	public void setUp() {
		beanFactory = new DefaultBeanFactory();

		reader = new XmlBeanDefinitionReader(beanFactory);
	}

	@Test
	public void testGetBean() {

		// 通过 XML 解析器去加载配置文件
		reader.loadBeanDefinition(new ClasspathResource("petstore-v1.xml"));

		// 获取 Bean 的定义
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");

		Assert.assertEquals("org.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());
		
		// 获取 Bean
		PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");
		
		Assert.assertNotNull(petStoreService);
		
	}
	
	@Test(expected = BeanCreationException.class)
	public void testInvalidBean() {

		reader.loadBeanDefinition(new ClasspathResource("petstore-v1.xml"));

		beanFactory.getBean("invalidBean");
		
		Assert.fail("expect BeanCreationException");
	}
	
	@Test(expected = BeanDefinitionStoreException.class)
	public void testInvalidXml() {

		reader.loadBeanDefinition(new ClasspathResource("xxx.xml"));

	}

}

package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;



public class BeanFactoryTest {

	@Test
	public void testGetBean() {
		
		// 加载配置文件
		BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
		
		// 获取 Bean 的定义
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");

		Assert.assertEquals("org.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());
		
		// 获取 Bean
		PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");
		
		Assert.assertNotNull(petStoreService);
		
	}
	
	@Test(expected = BeanCreationException.class)
	public void testInvalidBean() {
		
		BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
		
		beanFactory.getBean("invalidBean");
		
		Assert.fail("expect BeanCreationException");
	}
	
	@Test(expected = BeanDefinitionStoreException.class)
	public void testInvalidXml() {
		
		new DefaultBeanFactory("xxx.xml");
		
	}

}

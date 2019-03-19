package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import litespring.factory.support.BeanDefinition;
import litespring.factory.support.Beanfactory;
import litespring.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;


public class BeanFactoryTest {

    @Test
    public  void testGetBean() {

        Beanfactory factory = new DefaultBeanFactory("petstore-v1.xml");

        BeanDefinition definition = factory.getBeanDefinition("petStore");

        Assert.assertNotNull(definition);

        PetStoreService petStore = (PetStoreService) factory.getBean("petStore");

        Assert.assertNotNull(petStore);

    }

}

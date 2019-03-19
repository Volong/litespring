package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import litespring.beans.BeanDefinition;
import litespring.beans.factory.Beanfactory;
import litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;


public class BeanFactoryTest {

    @Test
    public  void testGetBean() {

        Beanfactory factory = new DefaultBeanFactory("petstore-v1.xml");

        BeanDefinition definition = factory.getBeanDefinition("petStore");

        Assert.assertEquals("org.litespring.service.v1.PetStoreService", definition.getBeanClassName());

        PetStoreService petStore = (PetStoreService) factory.getBean("petStore");

        Assert.assertNotNull(petStore);

    }

}

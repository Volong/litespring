package org.litespring.test.v1;

import litespring.beans.factory.BeansCreationException;
import litespring.beans.factory.BeansDefinitionStoreException;
import org.junit.Assert;
import org.junit.Test;
import litespring.beans.BeanDefinition;
import litespring.beans.factory.Beanfactory;
import litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;


public class BeanFactoryTest {

    @Test
    public void testGetBean() {

        Beanfactory factory = new DefaultBeanFactory("petstore-v1.xml");

        BeanDefinition definition = factory.getBeanDefinition("petStore");

        Assert.assertEquals("org.litespring.service.v1.PetStoreService", definition.getBeanClassName());

        PetStoreService petStore = (PetStoreService) factory.getBean("petStore");

        Assert.assertNotNull(petStore);

    }

    @Test(expected = BeansCreationException.class)
    public void testInvalidBean() {

        DefaultBeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        factory.getBean("invalidBean");
    }

    @Test(expected = BeansDefinitionStoreException.class)
    public void testInvalidXml() {
        new DefaultBeanFactory("xxx.xml");
    }

}

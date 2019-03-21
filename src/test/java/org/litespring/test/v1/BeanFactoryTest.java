package org.litespring.test.v1;

import litespring.beans.BeanDefinition;
import litespring.beans.factory.BeansCreationException;
import litespring.beans.factory.BeansDefinitionStoreException;
import litespring.beans.factory.support.DefaultBeanFactory;
import litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.service.v1.PetStoreService;


public class BeanFactoryTest {

    DefaultBeanFactory factory;

    XMLBeanDefinitionReader reader;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();

        reader = new XMLBeanDefinitionReader(factory);
    }

    @Test
    public void testGetBean() {

        reader.loadBeanDefinition("petstore-v1.xml");

        BeanDefinition bd = factory.getBeanDefinition("petStore");

        Assert.assertEquals("org.litespring.service.v1.PetStoreService", bd.getBeanClassName());

        PetStoreService petStore = (PetStoreService) factory.getBean("petStore");

        Assert.assertNotNull(petStore);

    }

    @Test(expected = BeansCreationException.class)
    public void testInvalidBean() {

        reader.loadBeanDefinition("petstore-v1.xml");

        factory.getBean("invalidBean");
    }

    @Test(expected = BeansDefinitionStoreException.class)
    public void testInvalidXml() {
        reader.loadBeanDefinition("xxx.xml");
    }

}

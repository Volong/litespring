package org.litespring.test.v1;

import litespring.context.ApplicationContext;
import litespring.context.support.ClassPathXmlApplicationContext;
import litespring.context.support.FileSystemXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;
import org.litespring.service.v1.PetStoreService;

public class ApplicationContextTest {

    @Test
    public void testGetBean() {

        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStore);
    }

    @Test
    public void testGetBeanFromFileSystemContext() {
        ApplicationContext context = new FileSystemXmlApplicationContext("C:\\workspace\\litespring\\src\\test\\resources\\petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStore);
    }
}

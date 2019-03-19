package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.context.support.FileSystemApplicationContext;
import org.litespring.service.v1.PetStoreService;

public class ApplicationContextTest {

    @Test
    public void testGetBean() {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");

        PetStoreService petStore = (PetStoreService) ctx.getBean("petStore");

        Assert.assertNotNull(petStore);
    }

    @Test
    public void testGetBeanFromFileSystemContext() {
        ApplicationContext context = new FileSystemApplicationContext("C:\\workspace\\litespring\\src\\test\\resources\\petstore-v1.xml");

        PetStoreService petStore = (PetStoreService) context.getBean("petStore");

        Assert.assertNotNull(petStore);
    }
}

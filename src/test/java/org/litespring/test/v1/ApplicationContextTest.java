package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import litespring.context.ApplicationContext;
import litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v1.PetStoreService;

public class ApplicationContextTest {

    @Test
    public void testGetBean() {

        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStore);
    }
}

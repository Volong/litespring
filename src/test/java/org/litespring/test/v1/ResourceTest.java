package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClasspathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceTest {

    @Test
    public void testClassPathResource() throws FileNotFoundException {

        Resource resource = new ClasspathResource("petstore-v1.xml");

        InputStream in = resource.getInputStream();

        Assert.assertNotNull(in);
    }

    @Test
    public void testFileSystemResource() throws FileNotFoundException {
        Resource resource = new FileSystemResource("C:\\workspace\\litespring\\src\\test\\resources\\petstore-v1.xml");

        InputStream in = resource.getInputStream();

        Assert.assertNotNull(in);
    }
}


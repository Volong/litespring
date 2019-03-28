package org.litespring.test.v1;

import litespring.beans.core.io.ClassPathResource;
import litespring.beans.core.io.FileSystemResource;
import litespring.beans.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResourceTest {

    @Test
    public void testClassPathResource() throws IOException {

        Resource classPathResource = new ClassPathResource("petstore-v1.xml");
        InputStream is = classPathResource.getInputStream();

        Assert.assertNotNull(is);
    }

    @Test
    public void testFileSystemResource() throws IOException {

        Resource fileSystemResource = new FileSystemResource("C:\\workspace\\litespring\\src\\test\\resources\\petstore-v1.xml");
        InputStream inputStream = fileSystemResource.getInputStream();
        Assert.assertNotNull(inputStream);
    }
}

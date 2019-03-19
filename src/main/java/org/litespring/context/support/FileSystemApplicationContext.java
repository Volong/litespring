package org.litespring.context.support;

import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class FileSystemApplicationContext extends AbstractApplicationContext {

    DefaultBeanFactory factory;

    public FileSystemApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }


}

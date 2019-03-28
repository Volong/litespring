package litespring.context.support;

import litespring.beans.core.io.FileSystemResource;
import litespring.beans.core.io.Resource;
import litespring.beans.factory.support.DefaultBeanFactory;
import litespring.beans.factory.xml.XMLBeanDefinitionReader;
import litespring.context.ApplicationContext;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext {


    public FileSystemXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}

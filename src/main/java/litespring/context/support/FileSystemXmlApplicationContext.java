package litespring.context.support;

import litespring.beans.core.io.FileSystemResource;
import litespring.beans.core.io.Resource;
import litespring.beans.factory.support.DefaultBeanFactory;
import litespring.beans.factory.xml.XMLBeanDefinitionReader;
import litespring.context.ApplicationContext;

public class FileSystemXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;

    public FileSystemXmlApplicationContext(String path) {
        factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        Resource resource = new FileSystemResource(path);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}

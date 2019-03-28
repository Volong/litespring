package litespring.context.support;

import litespring.beans.core.io.ClassPathResource;
import litespring.beans.core.io.Resource;
import litespring.beans.factory.support.DefaultBeanFactory;
import litespring.beans.factory.xml.XMLBeanDefinitionReader;
import litespring.context.ApplicationContext;

public abstract class AbstractApplicationContext implements ApplicationContext {

    DefaultBeanFactory factory;

    public AbstractApplicationContext(String path) {
        factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        Resource resource = getResourceByPath(path);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
    
    protected abstract Resource getResourceByPath(String path);


}

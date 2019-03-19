package org.litespring.context.support;

import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.beans.support.BeanDefinitionRegistry;
import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.ClasspathResource;
import org.litespring.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return new ClasspathResource((path));
    }
}

package org.litespring.context.support;

import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.ClasspathResource;
import org.litespring.core.io.Resource;

public abstract  class AbstractApplicationContext implements ApplicationContext {

    DefaultBeanFactory factory;

    public AbstractApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(getResourceByPath(configFile));
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    public abstract Resource getResourceByPath(String path);

}

package org.litespring.context.support;

import litespring.beans.factory.support.DefaultBeanFactory;
import litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.context.ApplicationContext;

public class ClassPathXmlApplicationContext implements ApplicationContext {

    DefaultBeanFactory factory;

    public ClassPathXmlApplicationContext(String path) {
        factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        reader.loadBeanDefinition(path);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}

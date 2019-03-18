package org.litespring.context.support;

import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.beans.support.BeanDefinitionRegistry;
import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.context.ApplicationContext;

public class ClassPathXmlApplicationContext implements ApplicationContext {

    DefaultBeanFactory factory;

    public ClassPathXmlApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(configFile);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}

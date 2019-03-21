package litespring.beans.factory.support;

import litespring.beans.BeanDefinition;
import litespring.beans.factory.BeanFactory;
import litespring.beans.factory.BeansCreationException;
import litespring.utils.ClassLoaderUtil;

import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    private static final ConcurrentHashMap<String, BeanDefinition> beanMaps = new ConcurrentHashMap<>();

    public DefaultBeanFactory() {

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanMaps.get(beanId);
    }

    @Override
    public void registryBeanDefinition(String id, BeanDefinition beanDefinition) {
        beanMaps.put(id, beanDefinition);
    }

    @Override
    public Object getBean(String beanId) {
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanMaps.get(beanId);

        if (beanDefinition == null) {
            throw  new BeansCreationException("Bean Definition does not exist");
        }
        String className = beanDefinition.getClassName();

        try {
            return ClassLoaderUtil.getClassLoader().loadClass(className).newInstance();
        } catch (Exception e) {
            throw new BeansCreationException("create bean for " + className, e);
        }
    }
}

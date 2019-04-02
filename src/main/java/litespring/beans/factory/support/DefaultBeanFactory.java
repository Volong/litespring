package litespring.beans.factory.support;

import litespring.beans.BeanDefinition;
import litespring.beans.factory.BeanFactory;
import litespring.beans.factory.BeansCreationException;
import litespring.beans.factory.config.ConfigurableBeanFactory;
import litespring.utils.ClassLoaderUtil;

import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private static final ConcurrentHashMap<String, BeanDefinition> beanMaps = new ConcurrentHashMap<>();

    private ClassLoader classLoader;

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
            return getBeanClassLoader().loadClass(className).newInstance();
        } catch (Exception e) {
            throw new BeansCreationException("create bean for " + className, e);
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.classLoader != null ? classLoader : ClassLoaderUtil.getClassLoader();
    }
}

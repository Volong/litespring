package litespring.context.support;

import litespring.beans.core.io.ClassPathResource;
import litespring.beans.core.io.Resource;
import litespring.beans.factory.support.DefaultBeanFactory;
import litespring.beans.factory.xml.XMLBeanDefinitionReader;
import litespring.context.ApplicationContext;
import litespring.utils.ClassLoaderUtil;

public abstract class AbstractApplicationContext implements ApplicationContext {

    DefaultBeanFactory factory;

    private ClassLoader classloader;

    public AbstractApplicationContext(String path) {
        factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        Resource resource = getResourceByPath(path);
        reader.loadBeanDefinition(resource);
        factory.setBeanClassLoader(getBeanClassLoader());
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classloader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.classloader != null ? this.classloader : ClassLoaderUtil.getClassLoader();
    }

    protected abstract Resource getResourceByPath(String path);


}

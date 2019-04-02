package litespring.context.support;

import litespring.beans.core.io.ClassPathResource;
import litespring.beans.core.io.Resource;
import litespring.beans.factory.support.DefaultBeanFactory;
import litespring.beans.factory.xml.XMLBeanDefinitionReader;
import litespring.context.ApplicationContext;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path, getBeanClassLoader());
    }

}

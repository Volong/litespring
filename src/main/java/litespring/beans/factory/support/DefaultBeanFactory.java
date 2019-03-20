package litespring.beans.factory.support;

import litespring.beans.BeanDefinition;
import litespring.beans.factory.Beanfactory;
import litespring.beans.factory.BeansCreationException;
import litespring.beans.factory.BeansDefinitionStoreException;
import litespring.utils.ClassLoaderUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements Beanfactory {

    private static final ConcurrentHashMap<String, BeanDefinition> beanMaps = new ConcurrentHashMap<>();

    public DefaultBeanFactory(String configFile) {

        InputStream stream = ClassLoaderUtil.getClassLoader().getResourceAsStream(configFile);

        SAXReader reader = new SAXReader();
        try {
            Document read = reader.read(stream);
            Element rootElement = read.getRootElement();

            Iterator<Element> iterator = rootElement.elementIterator();

            while (iterator.hasNext()) {

                Element next = iterator.next();
                String id = next.attributeValue("id");
                String className = next.attributeValue("class");

                BeanDefinition beanDefinition = new GenericBeanDefinition(id, className);
                beanMaps.put(id, beanDefinition);

            }
        } catch (DocumentException e) {
            throw new BeansDefinitionStoreException("IOException parsing xml document");
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanMaps.get(beanId);
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

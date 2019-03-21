package litespring.beans.factory.xml;

import litespring.beans.BeanDefinition;
import litespring.beans.factory.BeansDefinitionStoreException;
import litespring.beans.factory.support.BeanDefinitionRegistry;
import litespring.beans.factory.support.DefaultBeanFactory;
import litespring.beans.factory.support.GenericBeanDefinition;
import litespring.utils.ClassLoaderUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

public class XMLBeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    public XMLBeanDefinitionReader(DefaultBeanFactory factory) {
        this.registry = factory;
    }

    public void loadBeanDefinition(String path) {
        InputStream stream = ClassLoaderUtil.getClassLoader().getResourceAsStream(path);

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
                registry.registryBeanDefinition(id, beanDefinition);
                // beanMaps.put(id, beanDefinition);

            }
        } catch (DocumentException e) {
            throw new BeansDefinitionStoreException("IOException parsing xml document");
        }
    }
}

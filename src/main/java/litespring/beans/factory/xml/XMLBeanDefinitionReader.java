package litespring.beans.factory.xml;

import litespring.beans.BeanDefinition;
import litespring.beans.core.io.Resource;
import litespring.beans.factory.BeansDefinitionStoreException;
import litespring.beans.factory.support.BeanDefinitionRegistry;
import litespring.beans.factory.support.DefaultBeanFactory;
import litespring.beans.factory.support.GenericBeanDefinition;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;

public class XMLBeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    public XMLBeanDefinitionReader(DefaultBeanFactory factory) {
        this.registry = factory;
    }

    public void loadBeanDefinition(Resource resource) {
        // InputStream stream = ClassLoaderUtil.getClassLoader().getResourceAsStream(path);

        SAXReader reader = new SAXReader();
        try {
            Document read = reader.read(resource.getInputStream());
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
        } catch (Exception e) {
            throw new BeansDefinitionStoreException("IOException parsing xml document");
        }
    }
}

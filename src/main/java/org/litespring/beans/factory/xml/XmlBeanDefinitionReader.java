package org.litespring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.support.BeanDefinitionRegistry;
import org.litespring.beans.support.GenericBeanDefinition;
import org.litespring.utils.ClassUtils;

import java.io.InputStream;
import java.util.Iterator;

/**
 * 专门用于解析 XML。基于单一职责
 */
public class XmlBeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinition(String configFile) {

        try {
            ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
            InputStream is = defaultClassLoader.getResourceAsStream(configFile);
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(is);
            Element root = doc.getRootElement();

            Iterator<Element> elementIterator = root.elementIterator();

            while (elementIterator.hasNext()) {
                Element next = elementIterator.next();
                String id = next.attributeValue("id");
                String className = next.attributeValue("class");
                BeanDefinition bd = new GenericBeanDefinition(id, className);
                this.registry.registerBeanDefinition(id, bd);
            }

        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("parsing xml document error", e);
        }
    }

}


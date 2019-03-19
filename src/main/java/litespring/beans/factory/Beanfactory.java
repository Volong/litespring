package litespring.beans.factory;

import litespring.beans.BeanDefinition;

public interface Beanfactory {

    BeanDefinition getBeanDefinition(String petStore);

    Object getBean(String petStore);
}

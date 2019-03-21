package litespring.beans.factory.support;

import litespring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registryBeanDefinition(String id, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String petStore);

}

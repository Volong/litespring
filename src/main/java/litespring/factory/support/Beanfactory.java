package litespring.factory.support;

public interface Beanfactory {

    BeanDefinition getBeanDefinition(String petStore);

    Object getBean(String petStore);
}

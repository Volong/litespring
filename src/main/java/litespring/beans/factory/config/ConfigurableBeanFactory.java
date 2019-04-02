package litespring.beans.factory.config;

import litespring.beans.factory.BeanFactory;
import litespring.utils.ClassLoaderUtil;

public interface ConfigurableBeanFactory extends BeanFactory {

    public void setBeanClassLoader(ClassLoader classLoader);

    public ClassLoader getBeanClassLoader();
}

package litespring.beans.core.io;

import litespring.utils.ClassLoaderUtil;

import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource {

    private ClassLoader classLoader;

    private String path;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader == null ? ClassLoaderUtil.getClassLoader() : classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return classLoader.getResourceAsStream(path);
    }
}


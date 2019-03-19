package org.litespring.core.io;

import org.litespring.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ClasspathResource implements Resource {

    private String path;

    private ClassLoader classLoader;

    public ClasspathResource(String path) {
        this(path, null);
    }

    public ClasspathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream stream = this.classLoader.getResourceAsStream(path);

        if (stream == null) {
            throw new FileNotFoundException(path + " can't be opened");
        }

        return stream;
    }
}

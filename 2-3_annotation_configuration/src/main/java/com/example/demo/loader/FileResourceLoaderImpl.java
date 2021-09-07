package com.example.demo.loader;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileResourceLoaderImpl implements FileResourceLoader {

    private final Resource resource;

    public FileResourceLoaderImpl(String applicationLocale, ResourceLoader resourceLoader) {
        this.resource = resourceLoader.getResource(applicationLocale);
    }

    @Override
    public InputStream getResource() throws IOException {

        InputStream classPath = resource.getInputStream();

        return classPath;
    }
}

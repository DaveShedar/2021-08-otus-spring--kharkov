package com.example.demo.loader;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileResourceLoaderImpl implements FileResourceLoader {

    private final Resource resource;

    public FileResourceLoaderImpl(@Qualifier("applicationLocale") String applicationLocale, ResourceLoader resourceLoader) {
        this.resource = resourceLoader.getResource(applicationLocale);
    }

    @Override
    public InputStream getResource() {
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка в получении InputStream");
        }
        return null;
    }
}

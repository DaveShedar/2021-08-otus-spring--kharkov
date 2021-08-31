package loader;

import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;

public class FileResourceLoaderImpl implements FileResourceLoader {

    private final String filename;

    public FileResourceLoaderImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public InputStream getResource() throws IOException {

        InputStream classPath = new ClassPathResource(filename).getInputStream();

        return classPath;
    }
}

package loader;

import java.io.IOException;
import java.io.InputStream;

public interface FileResourceLoader {
    InputStream getResource() throws IOException;
}

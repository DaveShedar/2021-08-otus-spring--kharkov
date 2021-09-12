package loader;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FileResourseLoaderImplTest {

    @Test
    public void shouldReturnInputStream() throws IOException {
        FileResourceLoader fileResourceLoader = new FileResourceLoaderImpl("/test.csv");
        assertNotEquals(null, fileResourceLoader.getResource());
    }
}

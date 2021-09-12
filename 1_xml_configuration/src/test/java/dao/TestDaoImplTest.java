package dao;

import loader.FileResourceLoader;
import loader.FileResourceLoaderImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestDaoImplTest {

    @Test
    public void checkQuestionList() throws IOException {
        FileResourceLoader fileResourceLoader = new FileResourceLoaderImpl("/test.csv");
        TestDao testDao = new TestDaoImpl(fileResourceLoader);

        assertFalse(testDao.getTest().isEmpty());
    }
}

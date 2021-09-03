package dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import loader.FileResourceLoader;
import model.Tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestDaoImpl implements TestDao {
    private final FileResourceLoader fileResourceLoader;

    public TestDaoImpl(FileResourceLoader fileResourceLoader) {
        this.fileResourceLoader = fileResourceLoader;
    }


    public List<Tests> getTest() throws IOException {

        final InputStream inputStream = fileResourceLoader.getResource();

        try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            CsvToBean<Tests> csvToBean = new CsvToBeanBuilder<Tests>(br)
                    .withType(Tests.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }
}

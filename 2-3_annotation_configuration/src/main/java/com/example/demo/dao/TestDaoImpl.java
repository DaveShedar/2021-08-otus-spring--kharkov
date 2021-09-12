package com.example.demo.dao;

import com.example.demo.loader.FileResourceLoader;
import com.example.demo.model.Tests;
import com.example.demo.util.CsvReader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class TestDaoImpl implements TestDao {
    private final FileResourceLoader fileResourceLoader;
    private final CsvReader reader;

    public TestDaoImpl(FileResourceLoader fileResourceLoader, CsvReader reader) {
        this.fileResourceLoader = fileResourceLoader;
        this.reader = reader;
    }

    @Override
    public List<Tests> getTest() {
        try {
            return reader.getTestList(fileResourceLoader.getResource());
        } catch (IOException e){
            e.getStackTrace();
        }
        return null;
    }
}

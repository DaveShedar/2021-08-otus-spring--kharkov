package com.example.demo.util;

import com.example.demo.model.Tests;
import java.io.InputStream;
import java.util.List;

public interface CsvReader {

    List<Tests> getTestList(InputStream inputStream);
}

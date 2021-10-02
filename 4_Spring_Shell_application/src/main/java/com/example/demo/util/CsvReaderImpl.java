package com.example.demo.util;

import com.example.demo.model.Tests;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CsvReaderImpl implements CsvReader {

    @Override
    public List<Tests> getTestList(InputStream inputStream) {
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            CsvToBean<Tests> csvToBean = new CsvToBeanBuilder<Tests>(br)
                    .withType(Tests.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }
}

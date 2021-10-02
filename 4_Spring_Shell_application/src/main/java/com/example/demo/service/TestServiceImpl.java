package com.example.demo.service;

import com.example.demo.dao.TestDao;
import com.example.demo.model.Tests;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final TestDao dao;

    public TestServiceImpl(TestDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Tests> getTestList() {
            return dao.getTest();
    }
}

package service;

import dao.TestDao;
import model.Tests;
import java.io.IOException;
import java.util.List;

public class TestServiceImpl implements TestService {

    private final TestDao dao;

    public TestServiceImpl(TestDao dao) {
        this.dao = dao;
    }

    public List<Tests> getTestList() throws IOException {

        final List<Tests> testList = dao.getTest();
        return testList;
    }
}

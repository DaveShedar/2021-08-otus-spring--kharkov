package com.example.demo.dao;


import com.example.demo.model.Tests;

import java.io.IOException;
import java.util.List;

public interface TestDao {

    List<Tests> getTest() throws IOException;
}

package dao;

import model.Tests;
import java.io.IOException;
import java.util.List;

public interface TestDao {

    List<Tests> getTest() throws IOException;
}

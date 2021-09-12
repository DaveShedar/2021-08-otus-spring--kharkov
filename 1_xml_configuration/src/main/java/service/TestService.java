package service;

import model.Tests;
import java.io.IOException;
import java.util.List;

public interface TestService {

    List<Tests> getTestList() throws IOException;
}

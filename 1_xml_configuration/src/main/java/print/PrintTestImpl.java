package print;

import model.Tests;
import service.TestService;
import java.io.IOException;
import java.util.List;

public class PrintTestImpl implements PrintTest {

    private final TestService testService;

    public PrintTestImpl(TestService testService) {
        this.testService = testService;
    }

    @Override
    public void print() throws IOException {
        List<Tests> testList = testService.getTestList();
        testList.forEach((test) -> {
            System.out.println("Question number " + test.getId() + ":");
            System.out.println(test.getQuestion());
            System.out.println("1:" + test.getAnswer1());
            System.out.println("2:" + test.getAnswer2());
            System.out.println("3:" + test.getAnswer3());
            System.out.println("4:" + test.getAnswer4());
            System.out.println();
        });
    }
}

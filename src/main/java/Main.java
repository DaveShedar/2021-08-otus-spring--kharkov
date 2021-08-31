import org.springframework.context.support.ClassPathXmlApplicationContext;
import print.PrintTest;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        PrintTest printTest = context.getBean(PrintTest.class);
        printTest.print();
    }
}

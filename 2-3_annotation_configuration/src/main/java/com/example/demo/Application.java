package com.example.demo;

import com.example.demo.util.RunTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {

        ApplicationContext context = SpringApplication.run(Application.class, args);
        RunTest runTest = context.getBean(RunTest.class);

//        YAMLConfig yamlConfig = context.getBean(YAMLConfig.class);
//        System.out.println(yamlConfig.getFilepath());

        runTest.runTest();
    }
}

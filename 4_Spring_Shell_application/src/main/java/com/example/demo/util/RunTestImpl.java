package com.example.demo.util;

import com.example.demo.model.Tests;
import com.example.demo.service.TestService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;

@Service
public class RunTestImpl implements RunTest {

    private final MessageService messageService;
    private final String maxScore;
    private final List<Tests> testList;

    public RunTestImpl(TestService testService,
                       Environment environment,
                       MessageService messageService) {

        this.messageService = messageService;
        maxScore = environment.getProperty("application.maxPoints");
        testList = testService.getTestList();
    }

    @Override
    public void runTest() {
        try (Scanner scanner = new Scanner(System.in)) {
            int totalScore = 0;

            for (Tests t : testList) {

                System.out.println(messageService.getMessage("question.number", new Object[]{t.getId()}));
                System.out.println(t.getQuestion());
                System.out.println(messageService.getMessage("answer.option", null));
                System.out.println(messageService.getMessage("answer.first", new Object[]{t.getAnswer1()}));
                System.out.println(messageService.getMessage("answer.second", new Object[]{t.getAnswer2()}));
                System.out.println(messageService.getMessage("answer.third", new Object[]{t.getAnswer3()}));
                System.out.println(messageService.getMessage("answer.fourth", new Object[]{t.getAnswer4()}));
                int userAnswer = scanner.nextInt();

                if (userAnswer == t.getRightAnswer()) {
                    totalScore++;
                }
            }
            System.out.println(messageService.getMessage("test.over", null));
            System.out.println(messageService.getMessage("test.result", new Object[]{totalScore, maxScore}));
            System.exit(0);
        }
    }
}

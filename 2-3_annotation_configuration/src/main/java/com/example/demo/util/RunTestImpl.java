package com.example.demo.util;

import com.example.demo.model.Tests;
import com.example.demo.service.TestService;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

@Service
public class RunTestImpl implements RunTest {

    private final TestService testService;
    private final ReloadableResourceBundleMessageSource messageSource;
    private final Locale locale;
    private final String maxScore;
    private final List<Tests> testList;

    public RunTestImpl(TestService testService,
                       ReloadableResourceBundleMessageSource messageSource,
                       Environment environment) {
        this.testService = testService;
        this.messageSource = messageSource;
        locale = Locale.forLanguageTag(Objects.requireNonNull(environment.getProperty("application.locale.i18n")));
        maxScore = environment.getProperty("application.maxPoints");
        testList = testService.getTestList();
    }

    @Override
    public void runTest() {
        try (Scanner scanner = new Scanner(System.in)) {
            int totalScore = 0;
            System.out.println(messageSource.getMessage("user.name", null, locale));
            String userName = scanner.nextLine();
            System.out.println(messageSource.getMessage("greetings", new Object[]{userName}, locale));

            for (Tests t : testList) {
                System.out.println(messageSource.getMessage("question.number", new Object[]{t.getId()}, locale));
                System.out.println(t.getQuestion());
                System.out.println(messageSource.getMessage("answer.option", null, locale));
                System.out.println(messageSource.getMessage("answer.first", new Object[]{t.getAnswer1()}, locale));
                System.out.println(messageSource.getMessage("answer.second", new Object[]{t.getAnswer2()}, locale));
                System.out.println(messageSource.getMessage("answer.third", new Object[]{t.getAnswer3()}, locale));
                System.out.println(messageSource.getMessage("answer.fourth", new Object[]{t.getAnswer4()}, locale));
                int userAnswer = scanner.nextInt();

                if (userAnswer == t.getRightAnswer()) {
                    totalScore++;
                }
            }

            System.out.println(messageSource.getMessage("test.over", null, locale));
            System.out.println(messageSource.getMessage("test.result", new Object[]{totalScore, maxScore}, locale));
        }
    }
}

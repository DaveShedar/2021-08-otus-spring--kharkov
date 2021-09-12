package com.example.demo.util;

import lombok.Data;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@Data
@ShellComponent
public class ShellUtilImpl implements ShellUtil {

    private final RunTest runTest;

    @Override
    @ShellMethod(key = "My name is", value = "get user name")
    public void whatsYourName(@ShellOption({"username"}) String username) {
        System.out.println("Hello, " + username);
        System.out.println("Is it time to start the Test?");
    }

    @Override
    @ShellMethod(key = "yes", value = "confirm to run Test")
    public void isTimeToRunTest() {
        runTest.runTest();
    }

    @Override
    @ShellMethod(key = "no", value = "user wants to get some rest before Test")
    public void isNotTimeToRunTest() {
        System.out.println("Please get some rest, before running");
        System.out.println("Are you ready?");
    }
}

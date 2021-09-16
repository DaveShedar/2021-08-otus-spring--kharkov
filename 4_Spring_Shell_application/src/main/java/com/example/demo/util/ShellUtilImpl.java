package com.example.demo.util;

import lombok.Data;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@Data
@ShellComponent
public class ShellUtilImpl implements ShellUtil {

    private final RunTest runTest;
    private boolean IS_USER_GREETING = false;

    @Override
    @ShellMethod(key = "My name is", value = "get user name")
    public void whatsYourName(@ShellOption({"username"}) String username) {
        System.out.println("Hello, " + username);
        System.out.println("Is it time to start the Test?");
        IS_USER_GREETING = true;
    }

    @Override
    @ShellMethod(key = "yes", value = "confirm to run Test")
    public void isTimeToRunTest() {
        runTest.runTest();
    }

    @Override
    @ShellMethod(key = "no", value = "user wants to get some rest before Test starts")
    public void isNotTimeToRunTest() {
        System.out.println("Please get some rest, before running");
        System.out.println("Are you ready?");
    }

    @Override
    @ShellMethod("available command yes")
    public Availability isTimeToRunTestAvailability() {
        return IS_USER_GREETING ? Availability.available() : Availability.unavailable("You should write down your name, please");
    }

    @Override
    @ShellMethod("available command no")
    public Availability isNotTimeToRunTestAvailability() {
        return IS_USER_GREETING ? Availability.available() : Availability.unavailable("You should write down your name, please");
    }
}

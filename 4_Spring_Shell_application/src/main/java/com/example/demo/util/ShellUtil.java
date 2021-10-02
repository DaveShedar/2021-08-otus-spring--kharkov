package com.example.demo.util;

import org.springframework.shell.Availability;

public interface ShellUtil {
    void whatsYourName(String username);
    void isTimeToRunTest();
    void isNotTimeToRunTest();
    Availability isTimeToRunTestAvailability();
    Availability isNotTimeToRunTestAvailability();
}

package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("application.locale")
public class LocaleConfig {
    private static final String FILE_PATH = "questions/";
    private static final String FILE_EXTENSION = ".csv";

    private String i18n;

    public String getI18n() {
        return FILE_PATH + i18n + FILE_EXTENSION;
    }

    public void setI18n (String i18n) {
        this.i18n = i18n;
    }
}

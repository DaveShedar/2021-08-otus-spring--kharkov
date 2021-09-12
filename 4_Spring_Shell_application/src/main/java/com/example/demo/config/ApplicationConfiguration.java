package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@EnableConfigurationProperties({LocaleConfig.class})
public class ApplicationConfiguration {

    private final LocaleConfig localeConfig;

    @Autowired
    public ApplicationConfiguration(LocaleConfig localeConfig) {
        this.localeConfig = localeConfig;
    }

    @Bean
    public ReloadableResourceBundleMessageSource relResBundleMessageSource() {
        ReloadableResourceBundleMessageSource relResBundleMessageSource = new ReloadableResourceBundleMessageSource();
        relResBundleMessageSource.setBasename("classpath:/messages");
        relResBundleMessageSource.setDefaultEncoding("UTF-8");
        return relResBundleMessageSource;
    }

    @Bean("applicationLocale")
    public String applicationLocale() {
        return this.localeConfig.getI18n();
    }
}

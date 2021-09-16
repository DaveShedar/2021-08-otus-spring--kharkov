package com.example.demo.util;


import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Service
public class MessageServiceImpl implements MessageService {

    private final ReloadableResourceBundleMessageSource messageSource;
    private final Locale locale;

    public MessageServiceImpl(ReloadableResourceBundleMessageSource messageSource, Environment environment) {
        this.messageSource = messageSource;
        locale = Locale.forLanguageTag(Objects.requireNonNull(environment.getProperty("application.locale.i18n")));;
    }

    @Override
    public String getMessage(String message, Object[] arr) {
        return messageSource.getMessage(message, arr, locale);
    }

}

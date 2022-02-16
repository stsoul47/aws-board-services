package com.jslee.awsboardapiserver.project.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageSourceImpl {
    private final MessageSource messageSource;

    public String get(String code, Object[] args, Locale locale) {
        return messageSource.getMessage(code, args, locale);
    }
}

package com.etiya.emojigame.core.utils.messages;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageManager implements MessageService {

    private MessageSource messageSource;

    public MessageManager(MessageSource messageSource) { //Dependency Injection DI
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String message) {
        return this.messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
    }
}

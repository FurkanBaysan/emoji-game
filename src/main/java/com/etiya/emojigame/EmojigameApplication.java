package com.etiya.emojigame;

import com.etiya.emojigame.business.abstracts.UserService;
import com.etiya.emojigame.business.concretes.UserManager;
import com.etiya.emojigame.core.utils.exceptions.BusinessException;
import com.etiya.emojigame.core.utils.messages.MessageService;
import com.etiya.emojigame.core.utils.results.ErrorDataResult;
import com.etiya.emojigame.entities.User;
import com.etiya.emojigame.repositories.ScoreRepository;
import com.etiya.emojigame.repositories.UserRepository;
import org.hibernate.sql.Delete;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestControllerAdvice
@EnableScheduling
public class EmojigameApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmojigameApplication.class, args);
    }



    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    // We pass the type of error it will handle/catch to the parameter.
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());

        }
        return new ErrorDataResult<Object>(errors, methodArgumentNotValidException.getClass().getSimpleName());
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleBusinessException(BusinessException businessException) {
        return new ErrorDataResult<Object>(businessException.getMessage(), businessException.getClass().getSimpleName());
    }


}





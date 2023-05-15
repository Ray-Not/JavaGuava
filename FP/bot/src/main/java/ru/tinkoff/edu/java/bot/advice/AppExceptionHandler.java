package ru.tinkoff.edu.java.bot.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.dto.ApiErrorResponse;
import ru.tinkoff.edu.java.bot.exceptions.ChatNotFoundException;
import ru.tinkoff.edu.java.bot.exceptions.LinkIsNotRegisteredToChatException;

import java.util.Arrays;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({LinkIsNotRegisteredToChatException.class, ChatNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleNotFoundExceptions(RuntimeException exception) {
        return new ApiErrorResponse(
                "Error", HttpStatus.BAD_REQUEST.toString(), exception.getClass().getName(), exception.getMessage(), Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList().toArray(String[]::new));
    }

}
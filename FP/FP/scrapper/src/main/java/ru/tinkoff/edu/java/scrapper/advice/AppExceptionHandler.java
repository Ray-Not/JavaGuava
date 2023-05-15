package ru.tinkoff.edu.java.scrapper.advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import ru.tinkoff.edu.java.scrapper.dto.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.exception.ChatAlreadyExistException;
import ru.tinkoff.edu.java.scrapper.exception.ChatNotFoundException;
import ru.tinkoff.edu.java.scrapper.exception.LinkIsAlreadyAddedException;
import ru.tinkoff.edu.java.scrapper.exception.LinkNotFoundException;

import java.util.Arrays;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({LinkNotFoundException.class, ChatNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFoundExceptions(RuntimeException exception) {
        return new ApiErrorResponse(
            "Error",
            HttpStatus.NOT_FOUND.toString(),
            exception.getClass().getName(),
            exception.getMessage(),
            Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList().toArray(String[]::new)
        );
    }

    @ExceptionHandler({ChatAlreadyExistException.class, LinkIsAlreadyAddedException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleBadRequestExceptions(RuntimeException exception) {
        return new ApiErrorResponse(
            "Error",
            HttpStatus.BAD_REQUEST.toString(),
            exception.getClass().getName(),
            exception.getMessage(),
            Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList().toArray(String[]::new)
        );
    }
}
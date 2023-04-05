package ru.tinkoff.edu.java.bot.api.exceptionHandler;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.api.model.ApiErrorResponse;

@RestControllerAdvice
public class BotExceptionHandler {

    private String getDescription(String message) {
        ApiErrorResponse errorObj = new ApiErrorResponse(
                message,
                null,
                null,
                null,
                null
        );
        return errorObj.description();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String MessageNotReadable(HttpMessageNotReadableException Exception) {
        return getDescription("Некорректные значения параметров или их нет!");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String MethodNotSupported(HttpRequestMethodNotSupportedException Exception) {
        return getDescription("Метод не разрешен!");
    }
}

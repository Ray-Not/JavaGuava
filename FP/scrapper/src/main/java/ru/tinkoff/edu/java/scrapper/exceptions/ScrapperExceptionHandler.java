package ru.tinkoff.edu.java.scrapper.exceptions;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.EntryExsistException;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.EntryNotExsistException;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.NullLinkException;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.UnauthorizationException;
import ru.tinkoff.edu.java.scrapper.exceptions.model.ApiErrorResponse;

@RestControllerAdvice
public class ScrapperExceptionHandler {

    private ApiErrorResponse setException(Exception exception) {
        String[] parsing_class = exception.getClass().toString().split("\\.");
        String class_name = parsing_class[parsing_class.length - 1];
        ApiErrorResponse errorObj = new ApiErrorResponse(
                exception.getMessage(),
                class_name,
                exception.toString()
        );
        return errorObj;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse MessageNotReadable(HttpMessageNotReadableException e) {
        return setException(e);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse MethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return setException(e);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse MissingRequestHeader(MissingRequestHeaderException e) {
        return setException(e);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse ArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        return setException(e);
    }

    @ExceptionHandler(NullLinkException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse NullLink(NullLinkException e) {
        return setException(e);
    }

    @ExceptionHandler(UnauthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse Unauthorization(UnauthorizationException e) {
        return setException(e);
    }

    @ExceptionHandler(EntryExsistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ApiErrorResponse EntryExsist(EntryExsistException e) {
        return setException(e);
    }

    @ExceptionHandler(EntryNotExsistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse EntryNotExsist(EntryNotExsistException e) {
        return setException(e);
    }
}

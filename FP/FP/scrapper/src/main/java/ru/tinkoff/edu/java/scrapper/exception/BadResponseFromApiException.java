package ru.tinkoff.edu.java.scrapper.exception;

public class BadResponseFromApiException extends RuntimeException {

    public BadResponseFromApiException() {
    }

    public BadResponseFromApiException(String message) {
        super(message);
    }

}

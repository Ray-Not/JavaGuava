package ru.tinkoff.edu.java.scrapper.exception;

public class ChatAlreadyExistException extends RuntimeException {

    public ChatAlreadyExistException() {
    }

    public ChatAlreadyExistException(String message) {
        super(message);
    }
}

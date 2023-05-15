package ru.tinkoff.edu.java.scrapper.exception;

public class ChatNotFoundException extends RuntimeException {

    public ChatNotFoundException(String message) {
        super(message);
    }

    public ChatNotFoundException() {
    }

}

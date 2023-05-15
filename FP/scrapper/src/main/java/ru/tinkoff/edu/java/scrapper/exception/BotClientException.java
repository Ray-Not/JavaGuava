package ru.tinkoff.edu.java.scrapper.exception;

public class BotClientException extends RuntimeException{

    public BotClientException() {
    }

    public BotClientException(String message) {
        super(message);
    }
}
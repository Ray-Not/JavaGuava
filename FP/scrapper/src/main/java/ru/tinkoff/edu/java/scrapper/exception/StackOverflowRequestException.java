package ru.tinkoff.edu.java.scrapper.exception;

public class StackOverflowRequestException extends RuntimeException{

    public StackOverflowRequestException() {
        super();
    }

    public StackOverflowRequestException(String message) {
        super(message);
    }
}
package ru.tinkoff.edu.java.scrapper.exception;

public class LinkIsAlreadyAddedException extends RuntimeException {


    public LinkIsAlreadyAddedException(String message) {
        super(message);
    }

    public LinkIsAlreadyAddedException() {
    }
}

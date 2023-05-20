package ru.tinkoff.edu.java.scrapper.exceptions.customExceptions;

public class NullLinkException extends RuntimeException {

    public NullLinkException() {
        super();
    }

    public NullLinkException(String s) {
        super(s);
    }
}

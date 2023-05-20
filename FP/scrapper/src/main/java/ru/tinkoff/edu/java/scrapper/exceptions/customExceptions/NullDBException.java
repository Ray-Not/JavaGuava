package ru.tinkoff.edu.java.scrapper.exceptions.customExceptions;

public class NullDBException extends RuntimeException {

    public NullDBException() {
        super();
    }

    public NullDBException(String s) {
        super(s);
    }
}
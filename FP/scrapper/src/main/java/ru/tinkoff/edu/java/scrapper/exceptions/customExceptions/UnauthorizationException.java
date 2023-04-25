package ru.tinkoff.edu.java.scrapper.exceptions.customExceptions;

public class UnauthorizationException  extends RuntimeException {

    public UnauthorizationException() {
        super();
    }

    public UnauthorizationException(String s) {
        super(s);
    }
}

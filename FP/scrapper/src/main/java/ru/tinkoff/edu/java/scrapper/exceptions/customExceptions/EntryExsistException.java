package ru.tinkoff.edu.java.scrapper.exceptions.customExceptions;

public class EntryExsistException extends RuntimeException {

    public EntryExsistException() {
        super();
    }

    public EntryExsistException(String s) {
        super(s);
    }
}
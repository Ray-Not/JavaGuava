package ru.tinkoff.edu.java.scrapper.exceptions.customExceptions;

public class EntryNotExsistException extends RuntimeException {

    public EntryNotExsistException() {
        super();
    }

    public EntryNotExsistException(String s) {
        super(s);
    }
}
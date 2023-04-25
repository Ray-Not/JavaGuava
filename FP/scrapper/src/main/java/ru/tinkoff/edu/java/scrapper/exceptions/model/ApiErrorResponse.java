package ru.tinkoff.edu.java.scrapper.exceptions.model;

public record ApiErrorResponse(
        String exceptionMessage,
        String description,
        String exceptionName
) {}

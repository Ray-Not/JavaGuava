package ru.tinkoff.edu.java.scrapper.api.model;

import java.util.List;

public record ApiErrorResponse(
        String description,
        String code,
        String ExceptionName,
        String exceptionMessage,
        List<String> stacktrace
) {}

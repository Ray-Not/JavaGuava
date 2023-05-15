package ru.tinkoff.edu.java.scrapper.dto;

public record ApiErrorResponse(String description, String code, String exceptionName,
        String exceptionMessage, String[] stacktrace) {

}

package ru.tinkoff.edu.java.scrapper.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StackOverflowRecord(
        @JsonProperty("has_more") boolean has_more,
        @JsonProperty("items") List owner
) {}

package ru.tinkoff.edu.java.scrapper.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record WeatherRecord(
        @JsonProperty("location") HashMap location,
        @JsonProperty("current") HashMap current
        ) {}

package ru.tinkoff.edu.java.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserAddDto(@JsonProperty("username") String username,
                         @JsonProperty("first_name") String firstName,
                         @JsonProperty("last_name") String lastName) {
}
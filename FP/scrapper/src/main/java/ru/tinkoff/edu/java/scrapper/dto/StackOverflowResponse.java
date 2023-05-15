package ru.tinkoff.edu.java.scrapper.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;
public record StackOverflowResponse(List<StackOverflowItem> items) {

}

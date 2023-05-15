package ru.tinkoff.edu.java.scrapper.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
public record StackOverflowItem(@JsonProperty("last_edit_date") OffsetDateTime lastActivityDate,
        @JsonProperty("last_activity_date") OffsetDateTime lastEditDate) {

}

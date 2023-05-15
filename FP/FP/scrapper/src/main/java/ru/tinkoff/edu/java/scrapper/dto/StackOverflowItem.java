package ru.tinkoff.edu.java.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record StackOverflowItem(@JsonProperty("last_edit_date") OffsetDateTime lastEditDate,
        @JsonProperty("answer_count") int answerCount) {

@Override
public String toString() {
		return "StackOverflowItem{"
		+ "lastEditDate=" + lastEditDate
		+ ", answerCount=" + answerCount
		+ '}';
	}
}
package ru.tinkoff.edu.java.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record GitHubResponse(@JsonProperty("pushed") OffsetDateTime pushedAt,
                             @JsonProperty("updated") OffsetDateTime updatedAt,
                             @JsonProperty("full_name") String fullName,
							 @JsonProperty("description") String description,
							 @JsonProperty("forks_count") int forksCount
) {


@Override
public String toString() {
	return "GitHubResponse{" +
		"pushedAt=" + pushedAt +
		", updatedAt=" + updatedAt +
		", fullName='" + fullName + '\'' +
		", description='" + description + '\'' +
		", forksCount=" + forksCount +
		'}';
}

}

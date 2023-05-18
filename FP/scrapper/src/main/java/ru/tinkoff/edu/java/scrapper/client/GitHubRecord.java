package ru.tinkoff.edu.java.scrapper.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.HashMap;

public record GitHubRecord(
                           @JsonProperty("full_name") String name,
                           @JsonProperty("owner") HashMap owner,
                           @JsonProperty("private") boolean is_private,
                           @JsonProperty("node_id") String node_id,
                           @JsonProperty("html_url") String url,
                           @JsonProperty("description") String description,
                           @JsonProperty("created_at") OffsetDateTime createdAt,
                           @JsonProperty("updated_at") OffsetDateTime updatedAt,
                           @JsonProperty("pushed_at") OffsetDateTime pushedAt

) {}

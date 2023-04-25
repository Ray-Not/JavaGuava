package ru.tinkoff.edu.java.scrapper.jdbc.operations;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.api.model.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.jdbc.mappers.LinkMapper;

public interface ChatOperations {
    default void addChat(
            JdbcTemplate jdbcTemplate,
            AddLinkRequest addLinkRequest,
            Long chat
    ) {}

    default void removeChat(
            JdbcTemplate jdbcTemplate
    ){}

    default void findAllChat(
            JdbcTemplate jdbcTemplate,
            long chat
    ){}

    default void findChat(
            JdbcTemplate jdbcTemplate,
            AddLinkRequest addLinkRequest
    ){}
}

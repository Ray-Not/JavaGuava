package ru.tinkoff.edu.java.scrapper.jdbc.operations;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.api.model.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.jdbc.mappers.LinkMapper;

public interface LinkOperations {
    default void addLink(
            JdbcTemplate jdbcTemplate,
            AddLinkRequest addLinkRequest
    ) {
        Integer link_id = jdbcTemplate.query(
                "SELECT * FROM links WHERE id=(SELECT MAX(id) FROM links)",
                new LinkMapper()
        ).get(0).id() + 1;

        jdbcTemplate.update("INSERT INTO links VALUES(?, ?)", link_id, addLinkRequest.link());
    }

    default void removeLink(){}

    default void findAllLink(){}

    default void findLink(){}
}

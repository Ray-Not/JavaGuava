package ru.tinkoff.edu.java.scrapper.jdbc.operations;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.api.model.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.api.model.LinkResponse;
import ru.tinkoff.edu.java.scrapper.api.model.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.api.model.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.EntryNotExsistException;
import ru.tinkoff.edu.java.scrapper.jdbc.mappers.LinkMapper;

public interface LinkOperations {
    default void i_addLink(
            JdbcTemplate jdbcTemplate,
            AddLinkRequest addLinkRequest
    ) {
        int link_id;

        try {
            link_id = jdbcTemplate.query(
                    "SELECT * FROM links WHERE id=(SELECT MAX(id) FROM links)",
                    new LinkMapper()
            ).get(0).id() + 1;
        } catch (IndexOutOfBoundsException e) {
            link_id = 1;
        }

        jdbcTemplate.update("INSERT INTO links VALUES(?, ?)", link_id, addLinkRequest.link());
    }

    default void i_removeLink(
            JdbcTemplate jdbcTemplate,
            RemoveLinkRequest removeLinkRequest
    ){
        String query = "DELETE FROM links where link IN ('%s')";
        query = query.formatted(removeLinkRequest.link());
        jdbcTemplate.update(query);
    }

    default void i_findAllLink(
            JdbcTemplate jdbcTemplate,
            ListLinksResponse listLinksResponse
    ){}

    default int i_findLink(
            JdbcTemplate jdbcTemplate,
            String link
    ){
        try {
            String query = "SELECT * FROM links WHERE link IN ('%s')";
            query = query.formatted(link);
            return jdbcTemplate.query(
                    query,
                    new LinkMapper()
            ).get(0).id();
        } catch (IndexOutOfBoundsException e) { // Если значение не нашлось
            return 0;
        }
    }
}

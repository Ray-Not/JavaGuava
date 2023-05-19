package ru.tinkoff.edu.java.scrapper.jdbc.operations;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.model.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.model.LinkResponse;
import ru.tinkoff.edu.java.scrapper.model.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.jdbc.mappers.LinkMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    default List<LinkResponse> i_findAllLink(
            JdbcTemplate jdbcTemplate,
            ArrayList<Integer> links_ids
    ){
        try {
            String ids = Arrays.toString(links_ids.toArray());
            ids = ids.substring(1, ids.length() - 1);
            String query = "SELECT * FROM links WHERE id IN (%s)";
            query = query.formatted(ids);
            return jdbcTemplate.query(
                    query,
                    new LinkMapper()
            );
        } catch (IndexOutOfBoundsException e) { // Если значение не нашлось
            return null;
        }
    }

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

    default List<LinkResponse> i_getAllIds(JdbcTemplate jdbcTemplate) {
        try {
            String query = "SELECT * FROM links";
            return jdbcTemplate.query(query, new LinkMapper());
        } catch (IndexOutOfBoundsException e) { // Если значение не нашлось
            return null;
        }
    }
}

package ru.tinkoff.edu.java.scrapper.jdbc.operations;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.api.model.LinkChatResponse;
import ru.tinkoff.edu.java.scrapper.jdbc.mappers.LinkChatMapper;

import java.util.Objects;

public interface LinkChatOperations {
    default void addLinkChat(
            JdbcTemplate jdbcTemplate,
            int link_id,
            int chat_id
    ) {
        jdbcTemplate.update("INSERT INTO links_tgchats VALUES(?, ?)", link_id, chat_id);
    }

    default boolean findLinkChat(
            JdbcTemplate jdbcTemplate,
            int link_id,
            int chat_id
    ){
        try {
            String query = "SELECT * FROM links_tgchats WHERE linkid=(%d) AND chatid=(%d)";
            query = query.formatted(link_id, chat_id);
            jdbcTemplate.query(query, new LinkChatMapper()).get(0);
        } catch (IndexOutOfBoundsException e) { // Если значение не нашлось
            return true;
        }
        return false;
    }
}

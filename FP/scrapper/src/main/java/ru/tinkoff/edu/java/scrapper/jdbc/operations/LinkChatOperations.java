package ru.tinkoff.edu.java.scrapper.jdbc.operations;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.model.LinkChatResponse;
import ru.tinkoff.edu.java.scrapper.jdbc.mappers.LinkChatMapper;

import java.util.List;

public interface LinkChatOperations {
    default void addLinkChat(
            JdbcTemplate jdbcTemplate,
            int link_id,
            int chat_id
    ) {
        jdbcTemplate.update("INSERT INTO links_tgchats VALUES(?, ?)", link_id, chat_id);
    }

    default void i_removeLinkChat(
            JdbcTemplate jdbcTemplate,
            int link_id,
            int chat_id
    ) {
        String query = "DELETE FROM links_tgchats WHERE linkid=(%d) AND chatid=(%d)";
        query = query.formatted(link_id, chat_id);
        jdbcTemplate.update(query);
    }

    default void i_removeLinkChatAllChat(
            JdbcTemplate jdbcTemplate,
            int chat_id
    ) {
        String query = "DELETE FROM links_tgchats WHERE chatid=(%d)";
        query = query.formatted(chat_id);
        jdbcTemplate.update(query);
    }

    default boolean i_findLinkChat(
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

    default List<LinkChatResponse> i_get_all_links_for_chat(JdbcTemplate jdbcTemplate, int chat_id) {
        try {
            String query = "SELECT * FROM links_tgchats WHERE chatid=(%d)";
            query = query.formatted(chat_id);
            return jdbcTemplate.query(query, new LinkChatMapper());
        } catch (IndexOutOfBoundsException e) { // Если значение не нашлось
            return null;
        }
    }
}

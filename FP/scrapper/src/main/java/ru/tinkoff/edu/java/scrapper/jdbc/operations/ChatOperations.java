package ru.tinkoff.edu.java.scrapper.jdbc.operations;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.jdbc.mappers.ChatMapper;
import ru.tinkoff.edu.java.scrapper.jdbc.mappers.LinkMapper;

public interface ChatOperations {
    default void addChat(
            JdbcTemplate jdbcTemplate,
            long chat
    ) {
        int chat_id;

        try {
            chat_id = jdbcTemplate.query(
                    "SELECT * FROM tgchats WHERE id=(SELECT MAX(id) FROM tgchats)",
                    new ChatMapper()
            ).get(0).id() + 1;
        } catch (IndexOutOfBoundsException e) {
            chat_id = 1;
        }

        jdbcTemplate.update("INSERT INTO tgchats VALUES(?, ?)", chat_id, chat);
    }

    default void removeChat(
            JdbcTemplate jdbcTemplate,
            long chat
    ){}

    default int findChat(
            JdbcTemplate jdbcTemplate,
            long chat
    ){
        try {
            String query = "SELECT * FROM tgchats WHERE tg_chat_id=(%d)";
            query = query.formatted(chat);
            return jdbcTemplate.query(
                    query,
                    new ChatMapper()
            ).get(0).id();
        } catch (IndexOutOfBoundsException e) { // Если значение не нашлось
            return 0;
        }
    }
}

package ru.tinkoff.edu.java.scrapper.jdbc.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.api.model.ChatResponse;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatMapper implements RowMapper<ChatResponse> {
    @Override
    public ChatResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ChatResponse(rs.getLong("tg_chat_id"), rs.getInt("id"));
    }
}

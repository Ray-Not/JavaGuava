package ru.tinkoff.edu.java.scrapper.jdbc.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.api.model.LinkChatResponse;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkChatMapper implements RowMapper<LinkChatResponse> {
    @Override
    public LinkChatResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LinkChatResponse(rs.getInt("linkid"), rs.getInt("chatid"));
    }
}

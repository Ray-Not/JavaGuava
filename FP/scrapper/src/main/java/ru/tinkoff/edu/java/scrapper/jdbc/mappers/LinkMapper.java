package ru.tinkoff.edu.java.scrapper.jdbc.mappers;


import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.model.LinkResponse;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkMapper implements RowMapper<LinkResponse> {
    @Override
    public LinkResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LinkResponse(rs.getString("link"), rs.getInt("id"));
    }
}

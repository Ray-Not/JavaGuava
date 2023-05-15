package ru.tinkoff.edu.java.scrapper.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.model.jdbcAndJooq.Relation;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionRowMapper implements RowMapper<Relation> {

    @Override
    public Relation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Relation relation = new Relation();
        relation.setLinkId(rs.getLong("link_id"));
        relation.setChatId(rs.getLong("chat_id"));

        return relation;
    }
}
package ru.tinkoff.edu.java.scrapper.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.model.Relation;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SubscriptionRowMapper implements RowMapper<Relation> {

    @Override
    public Relation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Relation relation = new Relation();
        relation.setLinkId(rs.getLong("link_id"));
        relation.setChatId(rs.getLong("chat_id"));

        return relation;
    }
}
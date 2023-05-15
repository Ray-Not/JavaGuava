package ru.tinkoff.edu.java.scrapper.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.model.commonDto.Link;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkRowMapper implements RowMapper<Link> {

    @Override
    public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
        Link link = new Link();
        link.setId(rs.getLong("id"));
        link.setUrl(rs.getString("url"));
        link.setCheckedAt(rs.getTimestamp("checked_at"));
        link.setGhPushedAt(rs.getTimestamp("gh_pushed_at"));
        link.setGhDescription(rs.getString("gh_description"));
        link.setGhForksCount(rs.getInt("gh_forks_count"));
        link.setSoAnswerCount(rs.getInt("so_answer_count"));
        link.setSoLastEditDate(rs.getTimestamp("so_last_edit_date"));
        return link;
    }
}
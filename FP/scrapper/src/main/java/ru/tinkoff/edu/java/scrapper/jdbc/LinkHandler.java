package ru.tinkoff.edu.java.scrapper.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.api.model.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.api.model.LinkResponse;
import ru.tinkoff.edu.java.scrapper.jdbc.mappers.LinkMapper;

import java.util.List;

public class LinkHandler implements FindAllLink, AddLink, RemoveLink {
    @Override
    public void removeLink(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("DELETE FROM links_tgchats");
        jdbcTemplate.update("DELETE FROM links");
        jdbcTemplate.update("DELETE FROM tgchats");
    }
    @Override
    public List<LinkResponse> findAllLink(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.query("SELECT * FROM links", new LinkMapper());
    }
    @Override
    public void addLink(
            JdbcTemplate jdbcTemplate,
            AddLinkRequest addLinkRequest,
            Long tgChatId
    ) {
        jdbcTemplate.update("INSERT INTO links VALUES(1, ?)", addLinkRequest.link());
        jdbcTemplate.update("INSERT INTO tgchats VALUES(1, ?)", tgChatId);
        jdbcTemplate.update("INSERT INTO links_tgchats VALUES(1, 1)");
    }
}

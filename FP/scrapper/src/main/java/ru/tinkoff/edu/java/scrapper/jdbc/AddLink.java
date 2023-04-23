package ru.tinkoff.edu.java.scrapper.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.api.model.AddLinkRequest;

public interface AddLink {
    void addLink(
            JdbcTemplate jdbcTemplate,
            AddLinkRequest addLinkRequest,
            Long tgChatId
    );
}

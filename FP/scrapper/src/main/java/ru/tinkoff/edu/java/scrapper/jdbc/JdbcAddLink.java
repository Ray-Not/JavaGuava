package ru.tinkoff.edu.java.scrapper.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.linkparser.LinkParser;
import ru.tinkoff.edu.java.scrapper.api.model.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.NullLinkException;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkOperations;

public class JdbcAddLink implements LinkOperations {
    public void addLinkService(JdbcTemplate jdbc, AddLinkRequest link, Long chat) {
        LinkParser parser = new LinkParser();
        if (parser.getLink(link.link()) == null) {
            throw new NullLinkException("Ссылка невалидна");
        }

        addLink(jdbc, link);
    }
}

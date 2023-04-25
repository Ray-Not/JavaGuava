package ru.tinkoff.edu.java.scrapper.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.linkparser.LinkParser;
import ru.tinkoff.edu.java.scrapper.api.model.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.api.model.LinkResponse;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.EntryExsistException;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.NullDBException;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.NullLinkException;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.UnauthorizationException;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.ChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkOperations;

public class JdbcLinkService implements LinkOperations, ChatOperations, LinkChatOperations {
    public void addLink(JdbcTemplate jdbc, AddLinkRequest link, Long chat) {
        LinkParser parser = new LinkParser();
        if (parser.getLink(link.link()) == null) {
            throw new NullLinkException("Ссылка невалидна");
        }
        int link_id = findLink(jdbc, link.link());
        int chat_id = findChat(jdbc, chat);
        if (chat_id == 0) {
            throw new UnauthorizationException("Перед использованием нужно зарегистрироваться");
        }
        if (link_id == 0) {
            addLink(jdbc, link);
            link_id = findLink(jdbc, link.link());
        }
        if (findLinkChat(jdbc, link_id, chat_id)) {
            addLinkChat(jdbc, link_id, chat_id);
        }else throw new EntryExsistException("Ссылка уже добавлена!");
    }

    public String findLink(JdbcTemplate jdbc, LinkResponse linkResponse) {
        return findLink(jdbc, linkResponse);
    }
}

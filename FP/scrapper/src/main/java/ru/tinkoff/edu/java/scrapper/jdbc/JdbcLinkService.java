package ru.tinkoff.edu.java.scrapper.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.linkparser.LinkParser;
import ru.tinkoff.edu.java.scrapper.api.model.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.api.model.LinkResponse;
import ru.tinkoff.edu.java.scrapper.api.model.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.*;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.ChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkOperations;

public class JdbcLinkService implements LinkOperations, ChatOperations, LinkChatOperations {
    public void addLink(JdbcTemplate jdbc, AddLinkRequest link, Long chat) {
        LinkParser parser = new LinkParser();
        if (parser.getLink(link.link()) == null) {
            throw new NullLinkException("Ссылка невалидна");
        }
        int link_id = i_findLink(jdbc, link.link());
        int chat_id = i_findChat(jdbc, chat);
        if (chat_id == 0) {
            throw new UnauthorizationException("Перед использованием нужно зарегистрироваться");
        }
        if (link_id == 0) {
            i_addLink(jdbc, link);
            link_id = i_findLink(jdbc, link.link());
        }
        if (i_findLinkChat(jdbc, link_id, chat_id)) {
            addLinkChat(jdbc, link_id, chat_id);
        }else throw new EntryExsistException("Ссылка уже добавлена!");
    }

    public void removeLink(JdbcTemplate jdbc, RemoveLinkRequest link, long chat) {
        int link_id = i_findLink(jdbc, link.link());
        int chat_id = i_findChat(jdbc, chat);
        if (chat_id == 0) {
            throw new UnauthorizationException("Перед использованием нужно зарегистрироваться");
        }
        if (link_id != 0) {
            i_removeLink(jdbc, link);
        } else throw new EntryNotExsistException("Ссылки не существует");
        if (!i_findLinkChat(jdbc, link_id, chat_id)) {
            i_removeLinkChat(jdbc, link_id, chat_id);
        }
    }

}

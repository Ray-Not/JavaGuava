package ru.tinkoff.edu.java.bot.handler.commands;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.tinkoff.edu.java.bot.handler.DB;
import ru.tinkoff.edu.java.linkparser.LinkParser;
import ru.tinkoff.edu.java.scrapper.client.ClientConfiguration;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.EntryExsistException;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.EntryNotExsistException;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.NullLinkException;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.UnauthorizationException;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.ChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkOperations;
import ru.tinkoff.edu.java.scrapper.model.RemoveLinkRequest;

public interface Track extends LinkOperations, LinkChatOperations, ChatOperations {
    default String track(JdbcTemplate jdbc, String link, long chat) {
        ClientConfiguration client = new ClientConfiguration();
        LinkParser parser = new LinkParser();
        if (parser.getLink(link) == null) {
            return "Ссылка невалидна";
        }
        try {
            client.gitHubClient(parser.getLink(link));
        } catch (WebClientResponseException e) {
            try {
                client.stackOverflowClient(parser.getLink(link));
            } catch (WebClientResponseException ex) {
                return "Ссылка не поддерживается, доступны: Git, StackOverFlow";
            }
        }
        int link_id = i_findLink(jdbc, link);
        int chat_id = i_findChat(jdbc, chat);
        if (chat_id == 0) {
            return "Перед использованием нужно зарегистрироваться";
        }
        if (link_id == 0) {
            i_addLink(jdbc, link);
            link_id = i_findLink(jdbc, link);
        }
        if (i_findLinkChat(jdbc, link_id, chat_id)) {
            addLinkChat(jdbc, link_id, chat_id);
            return "Ссылка успешно добавлена!";
        }else return"Ссылка уже добавлена!";
    }
}

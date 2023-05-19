package ru.tinkoff.edu.java.scrapper.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.tinkoff.edu.java.linkparser.LinkParser;
import ru.tinkoff.edu.java.scrapper.model.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.model.LinkResponse;
import ru.tinkoff.edu.java.scrapper.model.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.client.ClientConfiguration;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.*;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.ChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkOperations;

import java.util.ArrayList;
import java.util.List;

public class JdbcLinkService implements LinkOperations, ChatOperations, LinkChatOperations {
    public void addLink(JdbcTemplate jdbc, AddLinkRequest link, Long chat) {
        ClientConfiguration client = new ClientConfiguration();
        LinkParser parser = new LinkParser();
        if (parser.getLink(link.link()) == null) {
            throw new NullLinkException("Ссылка невалидна");
        }
        try {
            client.gitHubClient(parser.getLink(link.link()));
        } catch (WebClientResponseException e) {
            try {
                client.stackOverflowClient(parser.getLink(link.link()));
            } catch (WebClientResponseException ex) {
                throw new NullLinkException("Ссылка не поддерживается, доступны: Git, StackOverFlow");
            }
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

    public List<LinkResponse> getLinks(JdbcTemplate jdbc, long chat) {
        int i;
        int chat_id = i_findChat(jdbc, chat);
        if (chat_id == 0) {
            throw new UnauthorizationException("Перед использованием нужно зарегистрироваться");
        }
        ArrayList<Integer> link_list = new ArrayList<>();
        for (i = 0; i < i_get_all_links_for_chat(jdbc, chat_id).size(); i++){
            link_list.add(i_get_all_links_for_chat(jdbc, chat_id).get(i).linkid());
        }
        return i_findAllLink(jdbc, link_list);
    }

}

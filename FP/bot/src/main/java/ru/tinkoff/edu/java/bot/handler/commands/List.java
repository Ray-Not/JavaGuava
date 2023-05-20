package ru.tinkoff.edu.java.bot.handler.commands;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.ChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkOperations;

import java.util.ArrayList;

public interface List extends LinkOperations, ChatOperations, LinkChatOperations {

    default String list(JdbcTemplate jdbc, long chat) {
        int i;
        String links = "";
        int chat_id = i_findChat(jdbc, chat);
        if (chat_id == 0) {
            return "Перед использованием нужно зарегистрироваться";
        }
        ArrayList<Integer> link_list = new ArrayList<>();
        try {
            for (i = 0; i < i_get_all_links_for_chat(jdbc, chat_id).size(); i++) {
                link_list.add(i_get_all_links_for_chat(jdbc, chat_id).get(i).linkid());
            }
            for (i = 0; i < i_findAllLink(jdbc, link_list).size(); i++) {
                links += i_findAllLink(jdbc, link_list).get(i).url() + "\n";
            }
            return links;
        } catch (BadSqlGrammarException e) {
            return "У вас нет ссылок";
        }
    }
}

package ru.tinkoff.edu.java.bot.handler.commands;

import ru.tinkoff.edu.java.bot.handler.DB;

public interface List {
    default String list() {
        if(DB.listIsEmpty()) return "list пустой";
        return "Отслеживаемые ссылки: <b>" + DB.getListParse() + "</b>";
    }
}

package ru.tinkoff.edu.java.bot.handler.commands;

import ru.tinkoff.edu.java.bot.handler.DB;

public interface Untrack {
    default String untrack(String link) {
        if(link.equals("")) return "ведите ссылку";
        if(DB.linkContain(link)) {
            DB.rmLink(link);
            return "ссылка <b>" + link + "</b> удалена";
        }
        return "ссылки " + link + " нет в пуле";
    }
}

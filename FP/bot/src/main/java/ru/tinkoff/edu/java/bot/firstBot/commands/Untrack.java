package ru.tinkoff.edu.java.bot.firstBot.commands;

import ru.tinkoff.edu.java.bot.firstBot.DB;

public interface Untrack {
    default String untrack(String link) {
        if(link.equals("")) return "ведите ссылку";
        if(DB.linkContain(link)) {
            DB.rmLink(link);
            return "ссылка " + link + " удалена";
        }
        return "ссылки " + link + " нет в пуле";
    }
}

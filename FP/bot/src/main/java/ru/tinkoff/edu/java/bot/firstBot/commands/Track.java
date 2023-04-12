package ru.tinkoff.edu.java.bot.firstBot.commands;

import ru.tinkoff.edu.java.bot.firstBot.DB;

public interface Track {
    default String track(String link) {
        link = link.trim();
        if(link.equals("")) return "ведите ссылку";
        if(DB.linkContain(link)) return "Ссылка уже есть";
        DB.addLink(link);
        return "ссылка <b>" + link + "</b> добавлена";
    }
}

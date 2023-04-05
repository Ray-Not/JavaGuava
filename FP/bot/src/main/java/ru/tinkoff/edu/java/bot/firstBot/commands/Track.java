package ru.tinkoff.edu.java.bot.firstBot.commands;

import ru.tinkoff.edu.java.bot.firstBot.DB;

import java.util.Objects;

public interface Track {
    default String track(String link) {
        link.trim();
        if(link.equals("")) return "ведите ссылку";
        if(DB.linkContain(link)) return "Ссылка уже есть";
        DB.addLink(link);
        return "ссылка " + link + " добавлена";
    }
}

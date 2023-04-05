package ru.tinkoff.edu.java.bot.firstBot.commands;

public class All implements List, Start, Track, Untrack, Help {
    protected String unknow() {
        return "Неизвестная команда";
    }
}

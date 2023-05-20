package ru.tinkoff.edu.java.bot.handler.commands;

public class All implements List, Start, Track, Untrack, Help {
    protected String unknow() {
        return "<b>Неизвестная команда</b>";
    }
}

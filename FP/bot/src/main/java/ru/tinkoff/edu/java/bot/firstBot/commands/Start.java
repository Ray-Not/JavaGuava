package ru.tinkoff.edu.java.bot.firstBot.commands;

public interface Start {
    
    default String start() {
        return "Бот начал работу";
    }
}


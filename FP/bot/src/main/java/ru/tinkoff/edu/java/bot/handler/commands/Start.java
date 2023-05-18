package ru.tinkoff.edu.java.bot.handler.commands;

public interface Start {
    
    default String start() {
        return "Бот начал работу";
    }
}


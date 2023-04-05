package ru.tinkoff.edu.java.bot.firstBot.commands;

public interface Help {
    default String help() {
        return "/start -- зарегистрировать пользователя\n" +
                "/help -- вывести окно с командами\n" +
                "/track -- начать отслеживание ссылки\n" +
                "/untrack -- прекратить отслеживание ссылки\n" +
                "/list -- показать список отслеживаемых ссылок";
    }
}

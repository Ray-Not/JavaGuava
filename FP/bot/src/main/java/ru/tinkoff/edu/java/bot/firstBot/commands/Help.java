package ru.tinkoff.edu.java.bot.firstBot.commands;

public interface Help {
    default String help() {
        return "<i>/start</i> -- зарегистрировать пользователя\n" +
                "<i>/help</i> -- вывести окно с командами\n" +
                "<i>/track [link]</i> -- начать отслеживание ссылки\n" +
                "<i>/untrack [link]</i> -- прекратить отслеживание ссылки\n" +
                "<i>/list</i> -- показать список отслеживаемых ссылок";
    }
}

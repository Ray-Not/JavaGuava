package ru.tinkoff.edu.java.bot.handler;

import ru.tinkoff.edu.java.bot.handler.commands.All;

public class MessageHandler extends All {

    public boolean is_command(String message) {
        return message.startsWith("/");
    }

    public String call_command(String command, String arg) {
        return switch (command) {
            case "/start" -> start();
            case "/help" -> help();
            case "/track" -> track(arg);
            case "/list" -> list();
            case "/untrack" -> untrack(arg);
            default -> unknow();
        };
    }

    public String call_command(String command) {
        return switch (command) {
            case "/start" -> start();
            case "/help" -> help();
            case "/list" -> list();
            default -> unknow();
        };
    }
}

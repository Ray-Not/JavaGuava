package ru.tinkoff.edu.java.bot.handler;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.bot.handler.commands.All;

public class MessageHandler extends All {

    private final JdbcTemplate jdbcTemplate;
    private final long chatid;

    public MessageHandler(JdbcTemplate jdbcTemplate, long chatid) {
        this.jdbcTemplate = jdbcTemplate;
        this.chatid = chatid;
    }

    public boolean is_command(String message) {
        return message.startsWith("/");
    }

    public String call_command(String command, String arg) {
        return switch (command) {
            case "/start" -> start(jdbcTemplate, chatid);
            case "/help" -> help();
            case "/track" -> track(jdbcTemplate, arg, chatid);
            case "/list" -> list(jdbcTemplate, chatid);
            case "/untrack" -> untrack(jdbcTemplate, arg, chatid);
            default -> unknow();
        };
    }

    public String call_command(String command) {
        return switch (command) {
            case "/start" -> start(jdbcTemplate, chatid);
            case "/help" -> help();
            case "/list" -> list(jdbcTemplate, chatid);
            default -> unknow();
        };
    }
}

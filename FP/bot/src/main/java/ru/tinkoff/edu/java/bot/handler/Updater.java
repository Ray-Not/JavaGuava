package ru.tinkoff.edu.java.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Updater implements UpdatesListener {

    MessageHandler handler;
    String command;
    TelegramBot bot;
    private JdbcTemplate jdbcTemplate;
    private long chatid;

    public Updater(TelegramBot bot, JdbcTemplate jdbcTemplate) {
        this.bot = bot;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int process(List<Update> updates) {
        Update update = updates.get(0);
        handler = new MessageHandler(jdbcTemplate, update.message().chat().id());
        if(handler.is_command(update.message().text())) {
            String[] parse = update.message().text().split(" ");
            if(parse.length > 1) command = handler.call_command(parse[0], parse[1]);
            else command = handler.call_command(parse[0]);
            bot.execute(
                    new SendMessage(update.message().chat().id(), command)
                            .replyMarkup(new ReplyKeyboardMarkup(new String[][]{
                                {"/start", "/help"},
                                {"/track testlink", "/untrack testlink", "/list"}
                            }).resizeKeyboard(true)
                            ).parseMode(ParseMode.HTML)
                    );
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

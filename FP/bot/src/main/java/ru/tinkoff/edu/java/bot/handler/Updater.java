package ru.tinkoff.edu.java.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.List;

public class Updater implements UpdatesListener {

    MessageHandler handler = new MessageHandler();
    String command;
    TelegramBot bot;

    public Updater(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public int process(List<Update> updates) {
        Update update = updates.get(0);
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

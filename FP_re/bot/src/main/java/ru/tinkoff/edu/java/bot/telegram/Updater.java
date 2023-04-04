package ru.tinkoff.edu.java.bot.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;

public abstract class Updater implements UpdateInterface {
    protected TelegramBot bot;

    public Updater(String token) {
        this.bot = new TelegramBot(token);
        this.bot.setUpdatesListener(updates -> {
            updates.forEach(this::handleUpdate);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public void sendMessage(Long chatId, String message){
        this.bot.execute(new SendMessage(chatId, message));
    }
}
package ru.tinkoff.edu.java.bot.telegram;


import com.pengrad.telegrambot.model.Update;

public interface UpdateInterface {
    void handleUpdate(Update update);
}

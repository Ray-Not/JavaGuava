package ru.tinkoff.edu.java.bot.firstBot;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;


@EnableConfigurationProperties(ApplicationConfig.class)
public class BotMain {

    String token;

    public BotMain(String token) {
        this.token = token;
    }

    public void start() {
        TelegramBot bot = new TelegramBot(token);
        bot.setUpdatesListener(new Updater(bot));
    }
}

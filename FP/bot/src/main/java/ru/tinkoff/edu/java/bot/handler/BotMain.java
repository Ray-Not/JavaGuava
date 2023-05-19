package ru.tinkoff.edu.java.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.bot.configuration.records.ApplicationConfig;


@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotMain {

    String token;
    static TelegramBot bot;
    private JdbcTemplate jdbcTemplate;

    public BotMain(String token, JdbcTemplate jdbcTemplate) {
        this.token = token;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void start() {
        bot = new TelegramBot(token);
        bot.setUpdatesListener(new Updater(bot, jdbcTemplate));
    }

    public void end() {
        bot.removeGetUpdatesListener();
    }
}

package ru.tinkoff.edu.java.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.bot.configuration.records.ApplicationConfig;


@EnableConfigurationProperties(ApplicationConfig.class)
public class BotMain {

    String token;
    static TelegramBot bot;

    public BotMain(String token) {
        this.token = token;
    }

    public void start() {
        bot = new TelegramBot(token);
        bot.setUpdatesListener(new Updater(bot));
    }

    public void end() {
        bot.removeGetUpdatesListener();
    }

    public static void apiCommand(long tgChatId, String command) {

        MessageHandler handler = new MessageHandler();
        String[] parse = command.split(" ");
        if(parse.length > 1) command = handler.call_command(parse[0], parse[1]);
        else command = handler.call_command(parse[0]);
        bot.execute(new SendMessage(tgChatId, command));
    }
}

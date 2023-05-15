package ru.tinkoff.edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.link_parser.parser.Link_Parser;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.client.ScrapperClientException;
import ru.tinkoff.edu.java.bot.dto.AddLinkRequest;

@Component
public class TrackCommand implements Command {	

    private final Link_Parser parser;

    public TrackCommand(ScrapperClient scrapperClient, LinkParser parser) {
        this.scrapperClient = scrapperClient;
        this.parser = parser;
    }

    private final ScrapperClient scrapperClient;

    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "начать отслеживание ссылки";
    }

    @Override
    public String handle(Update update) {
        long chatId = update.message().chat().id();
        String msg;
        try {
            if (parser.parseUrl(update.message().text()) != null){
                scrapperClient.addLink(chatId, new AddLinkRequest(update.message().text()));
                msg = "Ссылка успешно добавлена";
            } else msg = "Некорректная ссылка";
            return msg;
        } catch (ScrapperClientException e) {
            return e.getMessage();
        }
    }
}
package ru.tinkoff.edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.link_parser.parser.Link_Parser;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.client.ScrapperClientException;
import ru.tinkoff.edu.java.bot.dto.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.RemoveLinkRequest;

@Component
public class UntrackCommand implements Command{

    private final ScrapperClient scrapperClient;
    
    private final Link_Parser parser;


    public UntrackCommand(ScrapperClient scrapperClient, Link_Parser parser) {
        this.scrapperClient = scrapperClient;
        this.parser = parser;
    }

    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "прекратить отслеживание ссылки";
    }

    @Override
    public String handle(Update update) {
        long chatId = update.message().chat().id();
        String msg;
        try {
            if (parser.parseUrl(update.message().text()) != null){
                scrapperClient.deleteLink(chatId, new RemoveLinkRequest(update.message().text()));
                msg = "Ссылка успешно удалена";
            } else msg = "Некорректная ссылка";
            return msg;
        } catch (ScrapperClientException e) {
            return e.getMessage();
        }
    }
}
package ru.tinkoff.edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.client.ScrapperClientException;
import ru.tinkoff.edu.java.bot.dto.Link;
import ru.tinkoff.edu.java.bot.dto.ListLinkResponse;

@Component
public class ListCommand implements Command {

    private final ScrapperClient scrapperClient;

    public ListCommand(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "показать список отслеживаемых ссылок";
    }

    @Override
    public String handle(Update update) {
        long chatId = update.message().chat().id();
        try {
            ListLinkResponse response = scrapperClient.getLinks(chatId);
            StringBuilder msg = new StringBuilder();
            if (response.size() == 0)
                msg.append("Список отслеживаемых ссылок пуст!");
            else {
                msg.append("Ссылок отслеживается - ").append(response.size()).append("\n\n");
                for (Link link : response.links()) {
                    msg.append(link.url()).append("\n\n");
                }
            }
            return msg.toString();
        } catch (ScrapperClientException e){
            return e.getMessage();
        }
    }
}

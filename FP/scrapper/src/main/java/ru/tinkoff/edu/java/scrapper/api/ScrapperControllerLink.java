package ru.tinkoff.edu.java.scrapper.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.firstBot.BotMain;
import ru.tinkoff.edu.java.bot.firstBot.DB;
import ru.tinkoff.edu.java.scrapper.api.model.*;

@RequestMapping("/links")
@RestController
public class ScrapperControllerLink {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void linksDelete(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody RemoveLinkRequest removeLinkRequest
    ) {
        BotMain.apiCommand(tgChatId, "/untrack" + " " + removeLinkRequest.link());
    }

    @GetMapping
    public void linksGet(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
        BotMain.apiCommand(tgChatId, "/list");
    }

    @PostMapping
    public void linksPost(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody AddLinkRequest addLinkRequest
    ) {
        BotMain.apiCommand(tgChatId, "/track" + " " + addLinkRequest.link());
    }
}

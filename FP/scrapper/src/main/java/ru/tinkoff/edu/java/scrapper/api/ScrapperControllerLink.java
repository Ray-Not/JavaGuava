package ru.tinkoff.edu.java.scrapper.api;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.firstBot.BotMain;
import ru.tinkoff.edu.java.scrapper.api.model.*;
import ru.tinkoff.edu.java.scrapper.jdbc.AddLink;
import ru.tinkoff.edu.java.scrapper.jdbc.FindAllLink;
import ru.tinkoff.edu.java.scrapper.jdbc.LinkHandler;
import ru.tinkoff.edu.java.scrapper.jdbc.mappers.LinkMapper;

import java.util.List;

@RequestMapping("/links")
@RestController
public class ScrapperControllerLink  extends LinkHandler {
    private final JdbcTemplate jdbcTemplate;

    public ScrapperControllerLink(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void linksDelete(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody RemoveLinkRequest removeLinkRequest
    ) {
//        BotMain.apiCommand(tgChatId, "/untrack" + " " + removeLinkRequest.link());
        removeLink(jdbcTemplate);
    }

    @GetMapping
    public List<LinkResponse> linksGet(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
//        BotMain.apiCommand(tgChatId, "/list");
        return findAllLink(jdbcTemplate);
    }

    @PostMapping
    public void linksPost(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody AddLinkRequest addLinkRequest
    ) {
//        BotMain.apiCommand(tgChatId, "/track" + " " + addLinkRequest.link());
        addLink(jdbcTemplate, addLinkRequest, tgChatId);
    }
}

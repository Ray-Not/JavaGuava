package ru.tinkoff.edu.java.scrapper.api;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.firstBot.BotMain;
import ru.tinkoff.edu.java.bot.firstBot.DB;
import ru.tinkoff.edu.java.scrapper.api.model.*;

@RequestMapping("/links")
@RestController
public class ScrapperControllerLink {
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
        BotMain.apiCommand(tgChatId, "/untrack" + " " + removeLinkRequest.link());
        jdbcTemplate.update("DELETE FROM links WHERE link=?", removeLinkRequest.link());
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
//        BotMain.apiCommand(tgChatId, "/track" + " " + addLinkRequest.link());
        jdbcTemplate.update("INSERT INTO links VALUES(1, ?)", addLinkRequest.link());
        jdbcTemplate.update("INSERT INTO tgchats VALUES(1, ?)", tgChatId);
        jdbcTemplate.update("INSERT INTO links_tgchats VALUES(1, 1)");
    }
}

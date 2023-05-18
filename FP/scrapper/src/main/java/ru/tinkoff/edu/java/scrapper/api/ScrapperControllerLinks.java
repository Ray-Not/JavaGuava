package ru.tinkoff.edu.java.scrapper.api;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.api.model.*;
import ru.tinkoff.edu.java.scrapper.jdbc.JdbcLinkService;

import java.util.List;

@RequestMapping("/links")
@RestController
public class ScrapperControllerLinks {
    private final JdbcTemplate jdbcTemplate;
    JdbcLinkService linkService = new JdbcLinkService();

    public ScrapperControllerLinks(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void linksDelete(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody RemoveLinkRequest removeLinkRequest
    ) {
        linkService.removeLink(jdbcTemplate, removeLinkRequest, tgChatId);
    }

    @GetMapping
    public List<LinkResponse> linksGet(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
        return linkService.getLinks(jdbcTemplate, tgChatId);
    }

    @PostMapping
    public void linksPost(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody AddLinkRequest addLinkRequest
    ) {
        linkService.addLink(jdbcTemplate, addLinkRequest, tgChatId);
    }
}

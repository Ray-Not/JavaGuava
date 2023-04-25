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

    public ScrapperControllerLinks(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void linksDelete(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody RemoveLinkRequest removeLinkRequest
    ) {
//        removeLink(jdbcTemplate, tgChatId, removeLinkRequest);
    }

    @GetMapping
    public List<LinkResponse> linksGet(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
//        return findAllLink(jdbcTemplate, tgChatId);
        List<LinkResponse> list = null;
        return list;
    }

    @PostMapping
    public void linksPost(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody AddLinkRequest addLinkRequest
    ) {
        JdbcLinkService addService = new JdbcLinkService();
        addService.addLink(jdbcTemplate, addLinkRequest, tgChatId);
    }
}
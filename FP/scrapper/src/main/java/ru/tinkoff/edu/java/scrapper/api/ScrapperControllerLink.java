package ru.tinkoff.edu.java.scrapper.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.api.model.*;

@RequestMapping("/links")
@RestController
public class ScrapperControllerLink {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void linksDelete(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody RemoveLinkRequest removeLinkRequest
    ) {}

    @GetMapping
    public String linksGet(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
        return tgChatId + "";
    }

    @PostMapping
    public String linksPost(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody AddLinkRequest addLinkRequest
    ) {
        return addLinkRequest.link();
    }
}

package ru.tinkoff.edu.java.scrapper.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.ListLinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.exception.LinkNotFoundException;
import ru.tinkoff.edu.java.scrapper.model.commonDto.Link;
import ru.tinkoff.edu.java.scrapper.service.contract.SubscriptionService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/links")
public class LinkRestController {

    private final SubscriptionService subscriptionService;

    public LinkRestController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public ListLinkResponse getLinks(@RequestHeader("Tg-Chat-Id") Long chatId) {
        List<Link> list = subscriptionService.getLinksByChat(chatId);
        return new ListLinkResponse(list, list.size());
    }

    @PostMapping
    public LinkResponse addLink(@RequestHeader("Tg-Chat-Id") Long chatId, @RequestBody AddLinkRequest request) {
        Link link = subscriptionService.subscribe(chatId, URI.create(request.link()));
        return new LinkResponse(link.getId(), link.getUrl());

    }

    @DeleteMapping
    public LinkResponse deleteLink(@RequestHeader("Tg-Chat-Id") Long chatId, @RequestBody RemoveLinkRequest request) {
        Link link = subscriptionService.unsubscribe(chatId, URI.create(request.link()));
        if (link == null) {
            throw new LinkNotFoundException("Ссылка с таким url не отслеживается!");
        }
        return new LinkResponse(link.getId(), link.getUrl());
    }

}
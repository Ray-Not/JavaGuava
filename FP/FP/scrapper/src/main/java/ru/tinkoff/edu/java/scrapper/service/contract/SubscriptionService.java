package ru.tinkoff.edu.java.scrapper.service.contract;

import ru.tinkoff.edu.java.scrapper.model.commonDto.Link;
import java.net.URI;
import java.util.List;

public interface SubscriptionService {

    Link subscribe(Long chatId, URI url);

    Link unsubscribe(Long chatId, URI url);

    List<Link> getLinksByChat(Long chatId);

    List<Long> getChatIdsByLink(Long linkId);
}

package ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract;

import ru.tinkoff.edu.java.scrapper.model.commonDto.Link;
import ru.tinkoff.edu.java.scrapper.model.jdbcAndJooq.Relation;

import java.util.List;

public interface SubscriptionRepository {

	 List<Link> findLinksByChat(Long chatId);

	    List<Relation> findChatsByLink(Long linkId);

	    Relation findSubscription(Long linkId, Long chatId);

	    void addRelation(Relation relation);

	    void remove(Long linkId, Long chatId);

	    void removeAllByUser(Long chatId);
}
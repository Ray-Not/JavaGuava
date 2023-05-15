package ru.tinkoff.edu.java.scrapper.service.jdbcAndJooq.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.model.commonDto.Link;
import ru.tinkoff.edu.java.scrapper.model.jdbcAndJooq.Relation;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.LinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.SubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.service.contract.SubscriptionService;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;


@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {


    private final LinkRepository linkRepository;

    private final SubscriptionRepository subscriptionRepository;



    public SubscriptionServiceImpl(LinkRepository linkRepository, SubscriptionRepository subscriptionRepository) {
        this.linkRepository = linkRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    @Transactional
    public Link subscribe(Long chatId, URI url) {
        log.info("subscribe() method invocation in SubscriptionServiceImpl. chatId = "+chatId+" url = "+url.toString());
        Link link = linkRepository.findByUrl(url.toString());
        if (link == null) {
            link = new Link();
            link.setUrl(url.toString());
            link.setCheckedAt(new Timestamp(System.currentTimeMillis()));
            linkRepository.add(link);
            //обращение ниже нужно, чтобы получить id из БД
            link = linkRepository.findByUrl(link.getUrl());
        }
        Relation relation = subscriptionRepository.findSubscription(link.getId(), chatId);

        if (relation == null) {
            relation = new Relation();
            relation.setChatId(chatId);
            relation.setLinkId(link.getId());
            subscriptionRepository.addRelation(relation);
        }
        return link;
    }

    @Override
    @Transactional
    public Link unsubscribe(Long chatId, URI url) {
        log.info("unsubscribe() method invocation in SubscriptionServiceImpl. chatId = "+chatId+" url = "+url.toString());
        Link link = linkRepository.findByUrl(url.toString());

        if (link != null) {
//            linkRepository.remove(link.getId());
            subscriptionRepository.remove(link.getId(), chatId);
        }
        return link;
    }

    @Override
    public List<Link> getLinksByChat(Long chatId) {
        log.info("getLinksByChat() method invocation in SubscriptionServiceImpl. chatId = "+chatId);
        return subscriptionRepository.findLinksByChat(chatId);
    }

    @Override
    public List<Long> getChatIdsByLink(Long linkId) {
        log.info("getChatIdsByLink() method invocation in SubscriptionServiceImpl. linkId = "+linkId);
        return null;
    }

	@Override
	public Link add(Long chatId, URI url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Link remove(Long chatId, URI url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Link> getAllByUser(Long chatId) {
		// TODO Auto-generated method stub
		return null;
	}
}
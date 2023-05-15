package ru.tinkoff.edu.java.scrapper.service.jpa.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.exception.ChatNotFoundException;
import ru.tinkoff.edu.java.scrapper.exception.LinkNotFoundException;
import ru.tinkoff.edu.java.scrapper.model.commonDto.Link;
import ru.tinkoff.edu.java.scrapper.model.jpa.LinkEntity;
import ru.tinkoff.edu.java.scrapper.model.jpa.UserEntity;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaUserRepository;
import ru.tinkoff.edu.java.scrapper.service.contract.SubscriptionService;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JpaSubscriptionServiceImpl implements SubscriptionService {

    private final JpaLinkRepository linkRepository;

    private final JpaUserRepository userRepository;

    public JpaSubscriptionServiceImpl(JpaLinkRepository linkRepository, JpaUserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public Link subscribe(Long chatId, URI url) {
        log.info("subscribe() method invocation in JpaSubscriptionServiceImpl. chatId = "+chatId+" url = "+url.toString());
        Optional<LinkEntity> optionalLink = linkRepository.findByUrl(url.toString());
        LinkEntity linkToAdd = new LinkEntity();
        if (optionalLink.isEmpty()) {
            linkToAdd.setUrl(url.toString());
        } else {
            linkToAdd = optionalLink.get();
        }
        linkToAdd.setCheckedAt(new Timestamp(System.currentTimeMillis()));
        linkRepository.save(linkToAdd);

        Optional<UserEntity> optionalUser = userRepository.findById(chatId);
        if (optionalUser.isEmpty()) throw new ChatNotFoundException("Такой чат не зарегистрирован!");
        UserEntity user = optionalUser.get();
        List<LinkEntity> userLinks = user.getLinks();

        if (!userLinks.contains(linkToAdd)) {
            userLinks.add(linkToAdd);
        }

        userRepository.save(user);
        return Link.fromEntity(linkToAdd);
    }

    @Override
    @Transactional
    public Link unsubscribe(Long chatId, URI url) {
        log.info("unsubscribe() method invocation in JpaSubscriptionServiceImpl. chatId = "+chatId+" url = "+url.toString());
        Optional<UserEntity> optionalUser = userRepository.findByChatIdWithLinks(chatId);
        Optional<LinkEntity> optionalLink = linkRepository.findByUrl(url.toString());


        if (optionalUser.isEmpty()) throw new ChatNotFoundException("Такой пользователь не зарегистрирован");

        UserEntity user = optionalUser.get();

        List<LinkEntity> userLinks = user.getLinks();

        if (optionalLink.isEmpty() || !userLinks.contains(optionalLink.get()))
            throw new LinkNotFoundException("Такая ссылка не отслеживается");

        userLinks.remove(optionalLink.get());
        userRepository.save(user);

        return Link.fromEntity(optionalLink.get());
    }

    @Override
    public List<Link> getLinksByChat(Long chatId) {
        log.info("getAllByUser() method invocation in JpaSubscriptionServiceImpl. chatId = "+chatId);
        Optional<UserEntity> optionalUser = userRepository.findById(chatId);
        if (optionalUser.isEmpty()) throw new ChatNotFoundException("Такой чат не зарегистрирован!");
        return userRepository.findAllLinksByChat(chatId).stream().map(Link::fromEntity).toList();
    }


    @Override
    public List<Long> getChatIdsByLink(Long linkId) {
        log.info("getChatIdsByLink() method invocation in JpaSubscriptionServiceImpl. linkId = "+linkId);
        Optional<LinkEntity> optionalLink = linkRepository.findById(linkId);
        if (optionalLink.isEmpty()) throw new LinkNotFoundException("Такая ссылка не отслеживается");
        return linkRepository.findChatIdsByLinkId(linkId);
    }
}
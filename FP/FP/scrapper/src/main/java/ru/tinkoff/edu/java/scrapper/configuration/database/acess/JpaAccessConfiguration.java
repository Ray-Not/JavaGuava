package ru.tinkoff.edu.java.scrapper.configuration.database.acess;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.link_parser.Link_Parser;
import ru.tinkoff.edu.java.scrapper.client.BotClient;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.SubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.UserRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaUserRepository;
import ru.tinkoff.edu.java.scrapper.service.UpdateNotificationService;
import ru.tinkoff.edu.java.scrapper.service.contract.LinkUpdateService;
import ru.tinkoff.edu.java.scrapper.service.contract.SubscriptionService;
import ru.tinkoff.edu.java.scrapper.service.contract.TgChatService;
import ru.tinkoff.edu.java.scrapper.service.jdbcAndJooq.impl.TgChatServiceImpl;
import ru.tinkoff.edu.java.scrapper.service.jpa.impl.JpaLinkUpdateServiceImpl;
import ru.tinkoff.edu.java.scrapper.service.jpa.impl.JpaSubscriptionServiceImpl;
import ru.tinkoff.edu.java.scrapper.service.jpa.impl.JpaTgChatServiceImpl;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {

    @Bean
    public LinkUpdateService linkUpdateService(
            JpaLinkRepository linkRepository,
            Link_Parser linkParser,
            GitHubClient gitHubClient,
            StackOverflowClient stackOverflowClient,
            BotClient botClient) {
        return new JpaLinkUpdateServiceImpl(
                linkRepository,
                linkParser,
                gitHubClient,
                stackOverflowClient,
                botClient
        );
    }

    @Bean
    public SubscriptionService subscriptionService(
            JpaLinkRepository linkRepository,
            JpaUserRepository userRepository) {
        return new JpaSubscriptionServiceImpl(
                linkRepository,
                userRepository
        );
    }

    @Bean
    public TgChatService tgChatService(
            JpaUserRepository userRepository) {
        return new JpaTgChatServiceImpl(
                userRepository
        );
    }

}
package ru.tinkoff.edu.java.scrapper.configuration.database.acess;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.link_parser.Link_Parser;
import ru.tinkoff.edu.java.scrapper.client.BotClient;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.LinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.SubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.UserRepository;
import ru.tinkoff.edu.java.scrapper.repository.jooq.LinkJooqRepository;
import ru.tinkoff.edu.java.scrapper.repository.jooq.SubscriptionJooqRepository;
import ru.tinkoff.edu.java.scrapper.repository.jooq.UserJooqRepository;
import ru.tinkoff.edu.java.scrapper.service.UpdateNotificationService;
import ru.tinkoff.edu.java.scrapper.service.contract.LinkUpdateService;
import ru.tinkoff.edu.java.scrapper.service.contract.SubscriptionService;
import ru.tinkoff.edu.java.scrapper.service.contract.TgChatService;
import ru.tinkoff.edu.java.scrapper.service.jdbcAndJooq.impl.LinkUpdateServiceImpl;
import ru.tinkoff.edu.java.scrapper.service.jdbcAndJooq.impl.SubscriptionServiceImpl;
import ru.tinkoff.edu.java.scrapper.service.jdbcAndJooq.impl.TgChatServiceImpl;
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
public class JooqAccessConfiguration {


    @Bean
    public LinkRepository linkRepository(DSLContext dslContext){
        return new LinkJooqRepository(dslContext);
    }

    @Bean
    public SubscriptionRepository subscriptionRepository(DSLContext dslContext){
        return new SubscriptionJooqRepository(dslContext);
    }


    @Bean
    public UserRepository userRepository(DSLContext dslContext){
        return new UserJooqRepository(dslContext);
    }

    @Bean
    public LinkUpdateService linkUpdateService(
            LinkRepository linkRepository,
            SubscriptionRepository subscriptionRepository,
            LinkParser linkParser,
            GitHubClient gitHubClient,
            StackOverflowClient stackOverflowClient,
            BotClient botClient
    ) {
        return new LinkUpdateServiceImpl(
                linkRepository,
                subscriptionRepository,
                linkParser,
                gitHubClient,
                stackOverflowClient,
                botClient);
    }

    @Bean
    public SubscriptionService subscriptionService(
            LinkRepository linkRepository,
            SubscriptionRepository subscriptionRepository
    ) {
        return new SubscriptionServiceImpl(
                linkRepository,
                subscriptionRepository);
    }

    @Bean
    public TgChatService tgChatService(
            UserRepository userRepository,
            SubscriptionRepository subscriptionRepository
    ) {
        return new TgChatServiceImpl(
                userRepository,
                subscriptionRepository);
    }

}
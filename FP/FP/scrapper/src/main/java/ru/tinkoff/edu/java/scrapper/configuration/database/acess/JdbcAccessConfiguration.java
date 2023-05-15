package ru.tinkoff.edu.java.scrapper.configuration.database.acess;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.link_parser.Link_Parser;
import ru.tinkoff.edu.java.scrapper.client.BotClient;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.mapper.LinkRowMapper;
import ru.tinkoff.edu.java.scrapper.mapper.SubscriptionRowMapper;
import ru.tinkoff.edu.java.scrapper.mapper.UserRowMapper;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.LinkJdbcTemplateRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.SubscriptionJdbcTemplateRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.UserJdbcTemplateRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.LinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.SubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.UserRepository;
import ru.tinkoff.edu.java.scrapper.service.UpdateNotificationService;
import ru.tinkoff.edu.java.scrapper.service.contract.LinkUpdateService;
import ru.tinkoff.edu.java.scrapper.service.contract.SubscriptionService;
import ru.tinkoff.edu.java.scrapper.service.contract.TgChatService;
import ru.tinkoff.edu.java.scrapper.service.jdbcAndJooq.impl.LinkUpdateServiceImpl;
import ru.tinkoff.edu.java.scrapper.service.jdbcAndJooq.impl.SubscriptionServiceImpl;
import ru.tinkoff.edu.java.scrapper.service.jdbcAndJooq.impl.TgChatServiceImpl;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {

    @Bean
    public LinkRowMapper linkRowMapper() {
        return new LinkRowMapper();
    }

    @Bean
    public SubscriptionRowMapper subscriptionRowMapper() {
        return new SubscriptionRowMapper();
    }

    @Bean
    public UserRowMapper userRowMapper() {
        return new UserRowMapper();
    }

    @Bean
    public LinkRepository linkRepository(JdbcTemplate jdbcTemplate, LinkRowMapper linkRowMapper) {
        return new LinkJdbcTemplateRepository(jdbcTemplate, linkRowMapper);
    }

    @Bean
    public SubscriptionRepository subscriptionRepository(
        JdbcTemplate jdbcTemplate,
        SubscriptionRowMapper subscriptionRowMapper
    ) {
        return new SubscriptionJdbcTemplateRepository(jdbcTemplate, subscriptionRowMapper, linkRowMapper());
    }

    @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        return new UserJdbcTemplateRepository(jdbcTemplate, userRowMapper);
    }

    @Bean
    public LinkUpdateService linkUpdateService(
        LinkRepository linkRepository,
        SubscriptionRepository subscriptionRepository,
        LinkParser linkParser,
        GitHubClient gitHubClient,
        StackOverflowClient stackOverflowClient,
        UpdateNotificationService notificationService
    ) {
        return new LinkUpdateServiceImpl(
            linkRepository,
            subscriptionRepository,
            linkParser,
            gitHubClient,
            stackOverflowClient,
            notificationService
        );

    }

    @Bean
    public SubscriptionService subscriptionService(
        LinkRepository linkRepository,
        SubscriptionRepository subscriptionRepository
    ) {
        return new SubscriptionServiceImpl(
            linkRepository,
            subscriptionRepository
        );
    }

    @Bean
    public TgChatService tgChatService(
        UserRepository userRepository,
        SubscriptionRepository subscriptionRepository
    ) {
        return new TgChatServiceImpl(
            userRepository,
            subscriptionRepository
        );
    }
}
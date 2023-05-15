package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.client.BotClient;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "false")
public class HTTPConfiguration {

    @Value("${bot.baseurl}")
    private String botBaseUrl;


    @Bean
    public BotClient botClient(){return new BotClient(botBaseUrl);}

    @Bean
    public WebClient botWebClient(){
        return WebClient.create(botBaseUrl);
    }



}
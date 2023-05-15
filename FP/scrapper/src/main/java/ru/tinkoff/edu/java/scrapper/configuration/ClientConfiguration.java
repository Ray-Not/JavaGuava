package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.client.BotClient;

@Configuration
public class ClientConfiguration {

    @Value("${gh.baseurl}")
    private String gitHubBaseUrl;

    @Value("${so.baseurl}")
    private String stackOverflowBaseUrl;
    
    @Value("${bot.baseurl}")
    private String botBaseUrl;


    //Регистрируем клиентов как бины
    @Bean
    public GitHubClient gitHubClientService() {
        return new GitHubClient(gitHubBaseUrl);
    }

    @Bean
    public StackOverflowClient stackOverflowClientService() {
        return new StackOverflowClient(stackOverflowBaseUrl);
    }
    @Bean
    public WebClient ghWebClient(){
        return WebClient.create(gitHubBaseUrl);
    }

    @Bean
    public WebClient soWebClient(){
        return WebClient.create(stackOverflowBaseUrl);
    }

}
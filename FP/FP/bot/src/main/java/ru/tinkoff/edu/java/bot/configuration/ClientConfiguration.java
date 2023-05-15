package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;


@Configuration
public class ClientConfiguration {

    @Value("${scrapper.baseurl}")
    private String scrapperBaseUrl;


    @Bean
    public WebClient webClient(){
        return WebClient.create(scrapperBaseUrl);
    }

    //Регистрируем клиентов как бины
    @Bean
    public ScrapperClient scrapperClient() {
        return new ScrapperClient(scrapperBaseUrl);
    }
}
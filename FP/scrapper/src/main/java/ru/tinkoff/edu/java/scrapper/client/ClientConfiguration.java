package ru.tinkoff.edu.java.scrapper.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.linkparser.LinkParser;

import java.util.Objects;


@Configuration
public class ClientConfiguration {

    private final String BASE_GIT_URL = "https://github.com/Ray-Not/JavaGuava";
    private final String BASE_STACK_URL = "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c";
    @Value("${git.link}")
    String gitLink;
    @Value("${stack.link}")
    String stackLink;
    static WebClient.Builder builder = WebClient.builder();
    LinkParser pars = new LinkParser();

    @Bean
    public WeatherRecord weatherClient() {
        WeatherRecord weatherResponse = builder.build()
                .get()
                .uri("http://api.weatherapi.com/v1/current.json?key=3ff5d13401e44f30a14170938230204&q=Russia&aqi=no")
                .retrieve()
                .bodyToMono(WeatherRecord.class)
                .block();
        return weatherResponse;
    }

    public GitHubRecord gitHubClient(
            String link
    ) {
        return builder.build()
                .get()
                .uri("https://api.github.com/repos/" + link)
                .retrieve()
                .bodyToMono(GitHubRecord.class)
                .block();
    }

    public StackOverflowRecord stackOverflowClient(
            String link
    ) {
        String params = "?order=desc&sort=activity&site=stackoverflow";
        return builder.build()
                .get()
                .uri("https://api.stackexchange.com/2.3/questions/" + link + params)
                .retrieve()
                .bodyToMono(StackOverflowRecord.class)
                .block();
    }
}

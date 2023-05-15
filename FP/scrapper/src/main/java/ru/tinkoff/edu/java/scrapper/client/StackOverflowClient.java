package ru.tinkoff.edu.java.scrapper.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatus;
import ru.tinkoff.edu.java.scrapper.dto.StackOverflowItem;
import ru.tinkoff.edu.java.scrapper.dto.StackOverflowResponse;
import ru.tinkoff.edu.java.scrapper.exception.BadResponseFromApiException;
import ru.tinkoff.edu.java.scrapper.dto.GitHubResponse;
import ru.tinkoff.edu.java.scrapper.exception.GitHubRequestException;
import ru.tinkoff.edu.java.scrapper.exception.StackOverflowRequestException;

public class StackOverflowClient {

    @Value("${so.baseurl}")
    private String stackOverflowBaseUrl;

    private final WebClient webClient;


    //для использования baseUrl по умолчанию (берётся из properties)
    public StackOverflowClient() {
        this.webClient = WebClient.create(stackOverflowBaseUrl);
    }


    //можно указать базовый URL
    public StackOverflowClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public StackOverflowItem fetchQuestion(long id) {

        StackOverflowResponse response = webClient.get().uri("/questions/{id}?order=desc&sort=activity&site=stackoverflow", id).exchangeToMono(r->{
            if (!r.statusCode().equals(HttpStatus.OK)) throw new StackOverflowRequestException("Error with request to SO API");
            return r.bodyToMono(StackOverflowResponse.class);
        }).block();

        if (response == null || response.items().size() == 0)
            throw new BadResponseFromApiException("API StackOverflow returned bad response");

        return response.items().get(0);
    }
}

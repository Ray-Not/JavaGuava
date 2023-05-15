package ru.tinkoff.edu.java.bot.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.dto.*;


public class ScrapperClient {

    private final WebClient webClient;


    //По умолчанию - webClient инжектится из ClientConfiguration c baseUrl по умолчанию
    public ScrapperClient(WebClient webClient) {
        this.webClient = webClient;
    }

    //Здесь webClient не инжектится, чтобы была возможность указать baseUrl.
    public ScrapperClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }


    public ListLinkResponse getLinks(Long tgChatId) {
        ListLinkResponse response = webClient.get().uri("/links").header("Tg-Chat-Id", String.valueOf(tgChatId)).exchangeToMono(r -> {
            if (r.statusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ScrapperClientException("Чат с таким ID не зарегистрирован");
            } else if (r.statusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ScrapperClientException("Что-то пошло не так. Проблема на нашей стороне, повторите попытку позже");
            }
            return r.bodyToMono(ListLinkResponse.class);
        }).block();

        return response;
    }

    public LinkResponse addLink(Long tgChatId, AddLinkRequest request) {
        LinkResponse response = webClient.post().uri("/links").header("Tg-Chat-Id", String.valueOf(tgChatId))
                .bodyValue(request).exchangeToMono(r -> {
                    if (r.statusCode().equals(HttpStatus.BAD_REQUEST)) {
                        throw new ScrapperClientException("Ссылка с таким URL уже добавлена");
                    } else if (r.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new ScrapperClientException("Чат с таким ID не зарегистрирован");
                    } else if (r.statusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                        throw new ScrapperClientException("Что-то пошло не так. Проблема на нашей стороне, повторите попытку позже");
                    }
                    return r.bodyToMono(LinkResponse.class);
                }).block();

        return response;
    }

    public LinkResponse deleteLink(Long tgChatId, RemoveLinkRequest request) {
        LinkResponse response = webClient.method(HttpMethod.DELETE).uri("/links").header("Tg-Chat-Id", String.valueOf(tgChatId))
                .bodyValue(request).exchangeToMono(r -> {
                    if (r.statusCode().equals(HttpStatus.BAD_REQUEST)) {
                        throw new ScrapperClientException("Некорректно указана ссылка");
                    } else if (r.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new ScrapperClientException("Ссылка с таким URL не найдена или чат с таким ID не зарегистрирован");
                    } else if (r.statusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                        throw new ScrapperClientException("Что-то пошло не так. Проблема на нашей стороне, повторите попытку позже");
                    }
                    return r.bodyToMono(LinkResponse.class);
                }).block();

        return response;
    }


    public void registerChat(Long tgChatId, UserAddDto userAddDto) {
        webClient.post().uri("/tg-chat/{id}", tgChatId).bodyValue(userAddDto).exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ScrapperClientException("Некорректно указан ID или такой чат уже зарегистрирован");
            } else if (response.statusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ScrapperClientException("Что-то пошло не так. Проблема на нашей стороне, повторите попытку позже");
            }
            return Mono.empty();
        }).block();
    }

    public void deleteChat(Long tgChatId) {
        webClient.delete().uri("/tg-chat/{id}", tgChatId).exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ScrapperClientException("Некорректно указан ID");
            } else if (response.statusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ScrapperClientException("Чат с таким ID не найден");
            } else if (response.statusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ScrapperClientException("Что-то пошло не так. Проблема на нашей стороне, повторите попытку позже");
            }
            return Mono.empty();
        }).block();
    }


}
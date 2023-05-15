package ru.tinkoff.edu.java.scrapper.dto;

public record LinkUpdate(Long id, String url, String description, Long[] tgChatIds) {

}

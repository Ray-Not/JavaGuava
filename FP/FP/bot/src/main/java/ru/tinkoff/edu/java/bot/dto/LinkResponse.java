package ru.tinkoff.edu.java.bot.dto;

import java.net.URI;

public record LinkResponse(long id, URI url) {

    @Override
    public String toString() {
        return "LinkResponse{" + "id=" + id + ", url=" + url +'}';
    }
}
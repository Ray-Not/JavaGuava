package ru.tinkoff.edu.java.bot.dto;

import java.net.URI;

public record Link (Long id, URI url){
    @Override
    public String toString() {
        return "Link{" + "id=" + id + ", url='" + url + '\'' +'}';
    }
}
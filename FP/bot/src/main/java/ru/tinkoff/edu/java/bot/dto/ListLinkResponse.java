package ru.tinkoff.edu.java.bot.dto;

import java.util.List;

public record ListLinkResponse(List<Link> links, int size) {
}
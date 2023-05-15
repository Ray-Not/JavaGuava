package ru.tinkoff.edu.java.scrapper.dto;

import java.util.List;

public record StackOverflowResponse(List<StackOverflowItem> items) {

}

package ru.tinkoff.edu.java.scrapper.dto;

import ru.tinkoff.edu.java.scrapper.model.commonDto.Link;
import java.util.List;

public record ListLinkResponse(List<Link> links, int size) {

}

package ru.tinkoff.edu.java.scrapper.api.model;

import java.util.*;

public record ListLinksResponse(List<LinkResponse> links, int size) {}


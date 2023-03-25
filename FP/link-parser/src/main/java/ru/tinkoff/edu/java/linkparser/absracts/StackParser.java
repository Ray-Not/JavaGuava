package ru.tinkoff.edu.java.linkparser.absracts;

import java.util.Objects;

public class StackParser extends AbstractParser {

    @Override
    protected String parsAbstract(String link) {

        String[] parsed = link.split("/");

        if (!Objects.equals(parsed[2], "stackoverflow.com")) return null;

        if (parsed.length > 4) return parsed[4];

        return null;
    }
}

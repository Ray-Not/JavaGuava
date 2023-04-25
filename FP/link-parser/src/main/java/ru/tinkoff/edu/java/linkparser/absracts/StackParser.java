package ru.tinkoff.edu.java.linkparser.absracts;

import java.util.Objects;

public class StackParser extends AbstractParser {

    @Override
    protected String parsAbstract(String link) {

        String[] parsed = link.split("/");

        if (parsed.length < 5) return null;
        if (!Objects.equals(parsed[2], "stackoverflow.com")) return null;
        if (!Objects.equals(parsed[3], "questions")) return null;


        return parsed[4];
    }
}

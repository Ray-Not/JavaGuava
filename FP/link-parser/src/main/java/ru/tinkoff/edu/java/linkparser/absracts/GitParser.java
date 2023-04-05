package ru.tinkoff.edu.java.linkparser.absracts;

import java.util.Objects;

public class GitParser extends AbstractParser {

    @Override
    protected String parsAbstract(String link) {

        String[] parsed = link.split("/");

        if (parsed.length < 2) return null;

        if (!Objects.equals(parsed[2], "github.com")) return null;

        if (parsed.length > 4) return parsed[3] + "/" + parsed[4];

        return null;
    }
}

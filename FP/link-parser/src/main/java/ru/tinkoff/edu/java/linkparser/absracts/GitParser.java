package ru.tinkoff.edu.java.linkparser.absracts;

import java.util.Objects;

public class GitParser extends AbstractParser {

    @Override
    protected String parsAbstract (String link) {
        String[] link_list = link.split("/");
        if (link_list.length > 4 && Objects.equals(link_list[2], "github.com")) {
            return link_list[3] + " " + link_list[4];
        }
        return "";
    }
}

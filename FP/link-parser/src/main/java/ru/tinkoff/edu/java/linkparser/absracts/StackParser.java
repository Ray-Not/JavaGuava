package ru.tinkoff.edu.java.linkparser.absracts;

import java.util.Objects;

public class StackParser extends AbstractParser {

    @Override
    protected String parsAbstract (String link) {
        String[] link_list = link.split("/");
        if (link_list.length > 3 && Objects.equals(link_list[2], "stackoverflow.com"))
            if (Objects.equals(link_list[3], "questions")) return link_list[4];
        return "";
    } 
}

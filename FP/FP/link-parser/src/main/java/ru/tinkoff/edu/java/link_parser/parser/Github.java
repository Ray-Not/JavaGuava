package ru.tinkoff.edu.java.link_parser.parser;

import ru.tinkoff.edu.java.link_parser.link.GitHub_Link;
import ru.tinkoff.edu.java.link_parser.link.Parser_Link;

import java.net.URL;


public class Github extends Abstract {
    public Github(Abstract nextParser) {
        super(nextParser);
    }

    @Override
    public Parser_Link parseResult(URL url) {

        if (url.getHost().equals("github.com")) {
            String[] tokens = url.getFile().substring(1).split("/");
            if (tokens.length >= 2) {
                return new GitHub_Link(tokens[0], tokens[1]);
            }
        }

        if (nextParser != null) {
            return nextParser.parseResult(url);
        }

        return null;
    }
}
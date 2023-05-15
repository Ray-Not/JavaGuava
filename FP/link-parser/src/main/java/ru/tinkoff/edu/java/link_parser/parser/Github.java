package ru.tinkoff.edu.java.link_parser.parser;

import ru.tinkoff.edu.java.link_parser.link.GitHub_Link;
import ru.tinkoff.edu.java.link_parser.link.Parser_Link;

import java.net.URL;


public class Github extends Abstract {
    public Github(Abstract nextParser) {
        super(nextParser);
    }

    @Override
    public Parser_Link parser_Link(String url) {
    	URL toParse = tweakUrl(url);
        if (toParse == null) return null;

        if (toParse.getHost().equals("github.com")) {
            String[] tokens = toParse.getFile().substring(1).split("/");
            if (tokens.length >= 2) {
                return new GitHub_Link(tokens[0], tokens[1]);
            } else return null;
        }


        if (nextParser != null) return nextParser.parser_Link(url);

        return null;
    }
}
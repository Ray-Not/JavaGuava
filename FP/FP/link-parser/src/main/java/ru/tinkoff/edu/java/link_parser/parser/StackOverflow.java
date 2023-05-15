package ru.tinkoff.edu.java.link_parser.parser;

import ru.tinkoff.edu.java.link_parser.link.Parser_Link;
import ru.tinkoff.edu.java.link_parser.link.StackOverflow_Link;
import java.net.URL;

public class StackOverflow extends Abstract {
    public StackOverflow(Abstract nextParser) {
        super(nextParser);
    }

    @Override
    public Parser_Link parseResult(URL url) {
        if (url.getHost().equals("stackoverflow.com")) {
            String[] tokens = url.getFile().substring(1).split("/");
            if (tokens.length >= 2 && tokens[0].equals("questions")) {
                try {
                    return new StackOverflow_Link(Long.parseLong(tokens[1]));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }

        if (nextParser != null) {
            return nextParser.parseResult(url);
        }

        return null;
    }
}
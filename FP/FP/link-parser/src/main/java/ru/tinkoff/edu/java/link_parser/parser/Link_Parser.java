package ru.tinkoff.edu.java.link_parser.parser;

import ru.tinkoff.edu.java.link_parser.parser.Abstract;
import ru.tinkoff.edu.java.link_parser.parser.Github;
import ru.tinkoff.edu.java.link_parser.parser.StackOverflow;
import ru.tinkoff.edu.java.link_parser.link.Parser_Link;
import java.net.MalformedURLException;
import java.net.URL;

public class Link_Parser {
    public Parser_Link parseUrl(String url) {

        URL urlToCheck = tweakUrl(url);

        if (urlToCheck == null) {
            return null;
        }

        Abstract parser1 = new Github(null);
        Abstract parser2 = new StackOverflow(parser1);

        return parser2.parseResult(urlToCheck);
    }

    public final URL tweakUrl(String urlString) {
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            return null;
        }
        return url;
    }

}
package ru.tinkoff.edu.java.link_parser.parser;

import ru.tinkoff.edu.java.link_parser.parser.Abstract;
import ru.tinkoff.edu.java.link_parser.parser.Github;
import ru.tinkoff.edu.java.link_parser.parser.StackOverflow;
import ru.tinkoff.edu.java.link_parser.link.Parser_Link;

public class Link_Parser {
    public Parser_Link parseUrl(String url) {
        Abstract parser1 = new Github(null);
        Abstract parser2 = new StackOverflow(parser1);

        return parser2.parser_Link(url);
    }

}

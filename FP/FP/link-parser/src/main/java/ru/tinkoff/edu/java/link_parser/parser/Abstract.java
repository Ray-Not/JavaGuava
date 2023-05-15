package ru.tinkoff.edu.java.link_parser.parser;

import ru.tinkoff.edu.java.link_parser.link.Parser_Link;
import java.net.URL;


public abstract class Abstract {

    Abstract nextParser;

    public Abstract(Abstract nextParser) {
        this.nextParser = nextParser;
    }

    public abstract Parser_Link parseResult(URL url);
}
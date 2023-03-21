package ru.tinkoff.edu.java.linkparser.absracts;

import java.util.Objects;

public abstract class AbstractParser {
    private AbstractParser nextParser;

    public void setNextParser (AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    public void logParser (String link) {
        if (nextParser != null) {
            if (!Objects.equals(parsAbstract(link), "")) {
                System.out.println(parsAbstract(link));
            }
            nextParser.logParser(link);
        }
    }

    abstract protected String parsAbstract(String link);

}

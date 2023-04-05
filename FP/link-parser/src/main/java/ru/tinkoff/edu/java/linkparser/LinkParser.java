package ru.tinkoff.edu.java.linkparser;

import ru.tinkoff.edu.java.linkparser.absracts.*;

public class LinkParser {

    public String getLink(String link) {

        AbstractParser gitParser = new GitParser();
        AbstractParser stackParser = new StackParser();
        AbstractParser otherParser = new OtherParser();

        gitParser.setNextParser(stackParser);
        stackParser.setNextParser(otherParser);

        return gitParser.logParser(link);
    }
}

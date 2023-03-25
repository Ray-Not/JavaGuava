package ru.tinkoff.edu.java.linkparser;

import ru.tinkoff.edu.java.linkparser.absracts.*;

public class LinkParserDemo {

    public static void main (String [] args) {

        String link = "https://github.com/sanyarnd/tinkoff-java-course-2022/";
        String link2 = "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c";
        String link3 = "https://stackoverflow.com/search?q=unsupported%20link";

        AbstractParser gitParser = new GitParser();
        AbstractParser stackParser = new StackParser();
        AbstractParser otherParser = new OtherParser();

        gitParser.setNextParser(stackParser);
        stackParser.setNextParser(otherParser);

        System.out.println(gitParser.logParser(link3));
    }
}

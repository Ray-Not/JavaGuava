package ru.tinkoff.edu.java.linkparser.absracts;

public class OtherParser extends AbstractParser {

    @Override
    protected String parsAbstract(String link) {
        return "Ссылка не поддерживается";
    }
}

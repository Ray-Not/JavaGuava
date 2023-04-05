package ru.tinkoff.edu.java.linkparser.absracts;

public abstract class AbstractParser {

    private AbstractParser nextParser;

    public void setNextParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    public String logParser (String link) {
        if(nextParser != null) {
            if(this.parsAbstract(link) == null) return nextParser.logParser(link);
        }
        return this.parsAbstract(link);
    }

    abstract protected String parsAbstract(String link);

}

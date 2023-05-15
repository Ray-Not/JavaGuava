package ru.tinkoff.edu.java.link_parser.link;

public record GitHub_Link(String username, String repository) implements Parser_Link {

}
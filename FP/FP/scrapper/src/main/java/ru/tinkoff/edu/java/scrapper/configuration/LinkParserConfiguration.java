package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.link_parser.parser.Link_Parser;

@Configuration
public class LinkParserConfiguration {

    @Bean
    public Link_Parser linkParser(){
        return new Link_Parser();
    }
}
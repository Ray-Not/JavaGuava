package ru.tinkoff.edu.java.scrapper.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.api.model.LinkResponse;

import java.util.List;

public interface FindAllLink {
    List<LinkResponse> findAllLink(JdbcTemplate jdbcTemplate);

}

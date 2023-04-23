package ru.tinkoff.edu.java.scrapper.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

public interface RemoveLink {
    void removeLink(JdbcTemplate jdbcTemplate);
}

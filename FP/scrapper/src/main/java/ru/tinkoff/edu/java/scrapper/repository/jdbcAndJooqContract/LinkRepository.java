package ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract;

import ru.tinkoff.edu.java.scrapper.model.commonDto.Link;
import java.util.List;


public interface LinkRepository {
    List<Link> findAll();


    Link findByUrl(String url);
    void add(Link link);

    void updateCheckDate(Link link);
    void remove(Long id);
    List<Link> findOldLinks(Long timeUpdateDeltaInSeconds);

    void updateGhLink(Link link);

    void updateSoLink(Link link);
}

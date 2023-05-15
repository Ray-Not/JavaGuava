package ru.tinkoff.edu.java.scrapper.service.contract;

import ru.tinkoff.edu.java.scrapper.model.commonDto.Link;
import java.util.List;

public interface LinkUpdateService {


    List<Link> getOldLinks();

    void updateLinks();
}

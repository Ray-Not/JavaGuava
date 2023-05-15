package ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract;

import ru.tinkoff.edu.java.scrapper.model.commonDto.User;
import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findByChatId(Long id);
    void add(User user);
    void remove(Long id);

}

package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.mapper.UserRowMapper;
import ru.tinkoff.edu.java.scrapper.model.commonDto.User;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.UserRepository;
import java.util.List;

@Slf4j
public class UserJdbcTemplateRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final UserRowMapper userRowMapper;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public List<User> findAll() {
        log.info("findAll() method invocation in userJdbcRepo");
        String sql = "select * from \"user\"";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public User findByChatId(Long id) {
        log.info("findByChatId() method invocation in userJdbcRepo");
        String sql = "select * from \"user\" where \"user\".chat_id = ?";
        List<User> user = jdbcTemplate.query(sql, userRowMapper, id);
        return user.size() == 0 ? null : user.get(0);
    }

    @Override
    public void add(User user) {
        log.info("add() method invocation in userJdbcRepo");
        String sql = "insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getChatId(), user.getUsername(), user.getFirstName(), user.getLastName());
    }

    @Override
    public void remove(Long chatId) {
        log.info("remove() method invocation in userJdbcRepo");
        String sql = "delete from \"user\" where \"user\".chat_id = ?";
        jdbcTemplate.update(sql, chatId);
    }

}
package ru.tinkoff.edu.java.scrapper.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.model.commonDto.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setChatId(rs.getLong("chat_id"));
        user.setUsername(rs.getString("username"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        return user;
    }
}
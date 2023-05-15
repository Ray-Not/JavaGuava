package scrapper.jdbc;

import scrapper.environment.IntegrationEnvironment;
import scrapper.environment.TestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;
import ru.tinkoff.edu.java.scrapper.mapper.UserRowMapper;
import ru.tinkoff.edu.java.scrapper.model.User;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.UserJdbcTemplateRepository;

import java.util.List;

@SpringBootTest(classes = {ScrapperApplication.class, TestConfiguration.class})
public class JdbcUserTest extends IntegrationEnvironment {

    @Autowired
    private UserJdbcTemplateRepository userRepository;

    @Autowired
    private UserRowMapper userRowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    @Transactional
    @Rollback
    public void addUserTest() {
        List<User> beforeAddUser = jdbcTemplate.query("select * from \"user\"", userRowMapper);

        User user = new User(42L, "robtop21", "Robert","Polson");


        userRepository.add(user);

        List<User> afterAddUser = jdbcTemplate.query("select * from \"user\"", userRowMapper);

        Assertions.assertEquals(beforeAddUser.size(), 0);
        Assertions.assertEquals(afterAddUser.size(), 1);
        Assertions.assertNotNull(afterAddUser.get(0));
        Assertions.assertEquals(afterAddUser.get(0).getChatId(), 42L);
    }


    @Test
    @Transactional
    @Rollback
    public void removeUserTest() {
        List<User> beforeAddUser = jdbcTemplate.query("select * from \"user\"", userRowMapper);

        User user = new User(42L, "robtop21", "Robert","Polson");



        jdbcTemplate.update("insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)",
                user.getChatId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName());


        List<User> afterAddUser = jdbcTemplate.query("select * from \"user\"", userRowMapper);

        userRepository.remove(42L);

        List<User> afterRemoveUser = jdbcTemplate.query("select * from \"user\"", userRowMapper);


        Assertions.assertEquals(beforeAddUser.size(), 0);
        Assertions.assertEquals(afterAddUser.size(), 1);
        Assertions.assertNotNull(afterAddUser.get(0));
        Assertions.assertEquals(afterAddUser.get(0).getChatId(), 42L);
        Assertions.assertEquals(afterRemoveUser.size(), 0);
    }

    @Test
    @Transactional
    @Rollback
    public void findAllTest() {
        List<User> beforeAddUser = jdbcTemplate.query("select * from \"user\"", userRowMapper);


        for (int i = 0; i < 10; i++) {
            User user = new User(42L + i, "robtop21" + i, "Robert","Polson");

            jdbcTemplate.update("insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)",
                    user.getChatId(),
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName());
        }

        List<User> afterInsertionUser = userRepository.findAll();

        Assertions.assertEquals(beforeAddUser.size(), 0);
        Assertions.assertEquals(afterInsertionUser.size(), 10);
    }


    @Test
    @Transactional
    @Rollback
    public void findByChatIdTest(){
        List<User> beforeAddUser = jdbcTemplate.query("select * from \"user\"", userRowMapper);


        for (int i = 0; i < 10; i++) {
            User user = new User(42L + i, "robtop21" + i, "Robert","Polson");

            jdbcTemplate.update("insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)",
                    user.getChatId(),
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName());
        }

        List<User> afterInsertionUser = jdbcTemplate.query("select * from \"user\"", userRowMapper);

        User foundedByIdUser = userRepository.findByChatId(42L);

        Assertions.assertEquals(beforeAddUser.size(), 0);
        Assertions.assertEquals(afterInsertionUser.size(), 10);
        Assertions.assertNotNull(foundedByIdUser);

    }


}
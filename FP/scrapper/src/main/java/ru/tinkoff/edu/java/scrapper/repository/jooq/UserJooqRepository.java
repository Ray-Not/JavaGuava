package ru.tinkoff.edu.java.scrapper.repository.jooq;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.model.commonDto.User;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.UserRepository;
import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.User.*;

@Repository
@Slf4j
public class UserJooqRepository implements UserRepository {

    private final DSLContext dslContext;


    public UserJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<User> findAll() {
        log.info("findAll() method invocation in userJooqRepo");

        return dslContext.selectFrom(USER).fetchInto(User.class);
    }

    @Override
    public User findByChatId(Long id) {
        log.info("findByChatId() method invocation in userJooqRepo");

        return dslContext.selectFrom(USER)
                .where(USER.CHAT_ID.eq(id))
                .fetchOneInto(User.class);
    }

    @Override
    public void add(User user) {
        log.info("add() method invocation in userJooqRepo");

        dslContext.insertInto(USER)
                .set(USER.CHAT_ID, user.getChatId())
                .set(USER.USERNAME, user.getUsername())
                .set(USER.FIRST_NAME, user.getFirstName())
                .set(USER.LAST_NAME, user.getLastName())
                .execute();
    }

    @Override
    public void remove(Long chatId) {
        log.info("remove() method invocation in userJooqRepo");

        dslContext.deleteFrom(USER)
                .where(USER.CHAT_ID.eq(chatId))
                .execute();
    }
}

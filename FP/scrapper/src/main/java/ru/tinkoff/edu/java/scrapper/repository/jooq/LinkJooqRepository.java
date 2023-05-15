package ru.tinkoff.edu.java.scrapper.repository.jooq;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.model.commonDto.Link;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.LinkRepository;
import java.sql.Timestamp;
import java.util.List;
import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.*;

@Slf4j
public class LinkJooqRepository implements LinkRepository {


    private final DSLContext dslContext;



    public LinkJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<Link> findAll() {
        log.info("findAll() method invocation in linkJooqRepo");
        return dslContext.selectFrom(LINK)
                .fetchInto(Link.class);
    }

    @Override
    public Link findByUrl(String url) {
        log.info("findByUrl() method invocation in linkJooqRepo");
        return dslContext.selectFrom(LINK)
                .where(LINK.URL.eq(url))
                .fetchOneInto(Link.class);
    }

    @Override
    public void add(Link link) {
        log.info("add() method invocation in linkJooqRepo");
        dslContext.insertInto(LINK)
                .set(LINK.URL, link.getUrl())
                .set(LINK.CHECKED_AT, link.getCheckedAt().toLocalDateTime())
                .execute();
    }

    @Override
    public void updateCheckDate(Link link) {
        log.info("updateDate() method invocation in linkJooqRepo");
        dslContext.update(LINK)
                .set(LINK.CHECKED_AT, link.getCheckedAt().toLocalDateTime())
                .where(LINK.ID.eq(link.getId()))
                .execute();
    }

    @Override
    public void remove(Long id) {
        log.info("remove() method invocation in linkJooqRepo");
        dslContext.deleteFrom(LINK)
                .where(LINK.ID.eq(id))
                .execute();
    }

    @Override
    public List<Link> findOldLinks(Long timeUpdateDeltaInSeconds) {
        log.info("findOldLinks() method invocation in linkJooqRepo");
        Timestamp compareDate = new Timestamp(System.currentTimeMillis() - timeUpdateDeltaInSeconds * 1000);
        return dslContext.selectFrom(LINK)
                .where(LINK.CHECKED_AT.lessThan(compareDate.toLocalDateTime()))
                .orderBy(LINK.CHECKED_AT.desc())
                .fetchInto(Link.class);
    }

    @Override
    public void updateGhLink(Link link) {
        log.info("updateGhLink() method invocation in linkJooqRepo");
        dslContext.update(LINK)
        		.set(LINK.CHECKED_AT, link.getCheckedAt().toLocalDateTime())
        		.set(LINK.GH_FORKS_COUNT, link.getGhForksCount())
                .set(LINK.GH_DESCRIPTION, link.getGhDescription())
                .set(LINK.GH_PUSHED_AT, link.getGhPushedAt().toLocalDateTime())
                .where(LINK.ID.eq(link.getId()))
                .execute();
    }

    @Override
    public void updateSoLink(Link link) {
        log.info("updateSoLink() method invocation in linkJooqRepo");
        dslContext.update(LINK)
        		.set(LINK.CHECKED_AT, link.getCheckedAt().toLocalDateTime())
                .set(LINK.SO_LAST_EDIT_DATE, link.getSoLastEditDate().toLocalDateTime())
                .set(LINK.SO_ANSWER_COUNT, link.getSoAnswerCount())
                .where(LINK.ID.eq(link.getId()))
                .execute();
    }

}
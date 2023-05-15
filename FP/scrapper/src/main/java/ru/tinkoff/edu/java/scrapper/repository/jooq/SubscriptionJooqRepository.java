package ru.tinkoff.edu.java.scrapper.repository.jooq;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.model.Relation;
import ru.tinkoff.edu.java.scrapper.repository.SubscriptionRepository;

import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.UserLink.*;
import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.*;


@Repository
@Slf4j
public class SubscriptionJooqRepository implements SubscriptionRepository {


    private final DSLContext dslContext;


    public SubscriptionJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }


    @Override
    public List<Link> findLinksByChat(Long chatId) {
        log.info("findLinksByChat() method invocation in subscriptionJooqRepo");
        return dslContext.select()
                .from(LINK)
                .join(USER_LINK).on(LINK.ID.eq(USER_LINK.LINK_ID))
                .where(USER_LINK.CHAT_ID.eq(chatId))
                .fetchInto(Link.class);
    }

    @Override
    public List<Relation> findChatsByLink(Long linkId) {
        log.info("findChatsByLink() method invocation in subscriptionJooqRepo");
        return dslContext.select()
                .from(USER_LINK)
                .where(USER_LINK.LINK_ID.eq(linkId))
                .fetchInto(Relation.class);
    }

    @Override
    public Relation findSubscription(Long linkId, Long chatId) {
        log.info("findSubscription() method invocation in subscriptionJooqRepo");
        return dslContext.select()
                .from(USER_LINK)
                .where(USER_LINK.CHAT_ID.eq(chatId).and(USER_LINK.LINK_ID.eq(linkId)))
                .fetchOneInto(Relation.class);
    }

    @Override
    public void addRelation(Relation relation) {
        log.info("addRelation() method invocation in subscriptionJooqRepo");
        dslContext.insertInto(USER_LINK, USER_LINK.LINK_ID, USER_LINK.CHAT_ID)
                .values(relation.getLinkId(), relation.getChatId())
                .execute();
    }

    @Override
    public void remove(Long linkId, Long chatId) {
        log.info("remove() method invocation in subscriptionJooqRepo");
        dslContext.deleteFrom(USER_LINK)
                .where(USER_LINK.LINK_ID.eq(linkId).and(USER_LINK.CHAT_ID.eq(chatId)))
                .execute();
    }

    @Override
    public void removeAllByUser(Long chatId) {
        log.info("removeAllByUser() method invocation in subscriptionJooqRepo");
        dslContext.deleteFrom(USER_LINK)
                .where(USER_LINK.CHAT_ID.eq(chatId))
                .execute();
    }
}
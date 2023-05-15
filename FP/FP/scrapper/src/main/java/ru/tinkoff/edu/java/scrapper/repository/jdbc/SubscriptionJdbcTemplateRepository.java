package ru.tinkoff.edu.java.scrapper.repository.jdbc;


import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.mapper.LinkRowMapper;
import ru.tinkoff.edu.java.scrapper.mapper.SubscriptionRowMapper;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.model.Relation;
import ru.tinkoff.edu.java.scrapper.model.User;
import ru.tinkoff.edu.java.scrapper.repository.SubscriptionRepository;

import java.util.List;

@Repository
@Slf4j
public class SubscriptionJdbcTemplateRepository implements SubscriptionRepository {

    private final JdbcTemplate jdbcTemplate;

    private final SubscriptionRowMapper subscriptionRowMapper;

    private final LinkRowMapper linkRowMapper;

    public SubscriptionJdbcTemplateRepository(JdbcTemplate jdbcTemplate, SubscriptionRowMapper subscriptionRowMapper, LinkRowMapper linkRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.subscriptionRowMapper = subscriptionRowMapper;
        this.linkRowMapper = linkRowMapper;
    }

    @Override
    public List<Link> findLinksByChat(Long chatId) {
        log.info("findLinksByChat() method invocation in subscriptionRepo");
        String sql = "select * from link inner join user_link rel on link.id = rel.link_id where rel.chat_id = ?";
        return jdbcTemplate.query(sql, linkRowMapper, chatId);
    }

    @Override
    public List<Relation> findChatsByLink(Long linkId) {
        log.info("findChatsByLink() method invocation in subscriptionRepo");
        String sql = "select * from user_link where user_link.link_id = ?";
        return jdbcTemplate.query(sql, subscriptionRowMapper, linkId);
    }

    @Override
    public Relation findSubscription(Long linkId, Long chatId) {
        log.info("findSubscription() method invocation in subscriptionRepo");
        String sql = "select * from user_link rel where rel.chat_id = ? and rel.link_id = ?";
        List<Relation> relation = jdbcTemplate.query(sql, subscriptionRowMapper, chatId, linkId);
        return relation.size() == 0 ? null : relation.get(0);
    }


    @Override
    public void addRelation(Relation relation) {
        log.info("addRelation() method invocation in subscriptionRepo");
        String sql = "insert into user_link (link_id, chat_id) values(?, ?)";
        jdbcTemplate.update(sql, relation.getLinkId(), relation.getChatId());
    }

    @Override
    public void remove(Long linkId, Long chatId) {
        log.info("remove() method invocation in subscriptionRepo");
        String sql = "delete from user_link where user_link.link_id = ? and user_link.chat_id = ?";
        jdbcTemplate.update(sql, linkId, chatId);
    }

    @Override
    public void removeAllByUser(Long chatId) {
        log.info("removeAllByUser() method invocation in subscriptionRepo");
        String sql = "delete from user_link where user_link.chat_id = ?";
        jdbcTemplate.update(sql, chatId);
    }
}
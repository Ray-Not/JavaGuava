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
import ru.tinkoff.edu.java.scrapper.mapper.LinkRowMapper;
import ru.tinkoff.edu.java.scrapper.mapper.SubscriptionRowMapper;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.model.Relation;
import ru.tinkoff.edu.java.scrapper.model.User;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.LinkJdbcTemplateRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.SubscriptionJdbcTemplateRepository;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest(classes = {ScrapperApplication.class, TestConfiguration.class})
public class JdbcSubscriptionTest extends IntegrationEnvironment {

    @Autowired
    private SubscriptionJdbcTemplateRepository subscriptionRepository;

    @Autowired
    private LinkRowMapper linkRowMapper;

    @Autowired
    private SubscriptionRowMapper subscriptionRowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    @Transactional
    @Rollback
    public void findLinksByChatTest() {
        List<Link> beforeAddLink = jdbcTemplate.query("select * from link inner join user_link rel on link.id = rel.link_id where rel.chat_id = ?", linkRowMapper, 42L);

        jdbcTemplate.update("insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)", 42L, "robtop21", "Robert", "Polson");
        jdbcTemplate.update("insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)", 50L, "alucard", "Will", "Smith");

        //всего добавим 20 ссылок
        for (int i = 0; i < 20; i++) {
            Link linkToAdd = new Link();
            linkToAdd.setUrl("https://stackoverflow.com/questions/2336692/java-multiple-class-declarations-in-one-file" + i);
            linkToAdd.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            jdbcTemplate.update("insert into link (url, updated_at) values(?, ?)", linkToAdd.getUrl(), linkToAdd.getUpdatedAt());
        }

        List<Link> afterInsertionLink = jdbcTemplate.query("select * from link", linkRowMapper);

        //из 20-ти ссылок подпишемся лишь на 10
        for (int i = 0; i < 10; i++) {
            jdbcTemplate.update("insert into user_link (link_id, chat_id) values(?, ?)", afterInsertionLink.get(i).getId(), 42L);
        }

        //на оставшиеся 10 подпишем другого пользователя
        for (int i = 10; i < 20; i++) {
            jdbcTemplate.update("insert into user_link (link_id, chat_id) values(?, ?)", afterInsertionLink.get(i).getId(), 50L);
        }

        List<Link> userLinks = subscriptionRepository.findLinksByChat(42L);

        Assertions.assertEquals(beforeAddLink.size(), 0);
        Assertions.assertEquals(afterInsertionLink.size(), 20);
        Assertions.assertEquals(userLinks.size(), 10);
    }

    @Test
    @Transactional
    @Rollback
    public void removeRelationTest() {
        List<Link> beforeAddLink = jdbcTemplate.query("select * from link inner join user_link rel on link.id = rel.link_id where rel.chat_id = ?", linkRowMapper, 42L);

        jdbcTemplate.update("insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)", 42L, "robtop21", "Robert", "Polson");

        for (int i = 0; i < 20; i++) {
            Link linkToAdd = new Link();
            linkToAdd.setUrl("https://stackoverflow.com/questions/2336692/java-multiple-class-declarations-in-one-file" + i);
            linkToAdd.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            jdbcTemplate.update("insert into link (url, updated_at) values(?, ?)", linkToAdd.getUrl(), linkToAdd.getUpdatedAt());
        }

        List<Link> afterInsertionLink = jdbcTemplate.query("select * from link", linkRowMapper);

        for (int i = 0; i < 10; i++) {
            jdbcTemplate.update("insert into user_link (link_id, chat_id) values(?, ?)", afterInsertionLink.get(i).getId(), 42L);
        }

        List<Link> userLinksBeforeRemove = jdbcTemplate.query("select * from link inner join user_link rel on link.id = rel.link_id where rel.chat_id = ?", linkRowMapper, 42L);

        subscriptionRepository.remove(afterInsertionLink.get(0).getId(), 42L);
        subscriptionRepository.remove(afterInsertionLink.get(4).getId(), 42L);
        subscriptionRepository.remove(afterInsertionLink.get(6).getId(), 42L);

        List<Link> userLinksAfterRemove = jdbcTemplate.query("select * from link inner join user_link rel on link.id = rel.link_id where rel.chat_id = ?", linkRowMapper, 42L);

        Assertions.assertEquals(beforeAddLink.size(), 0);
        Assertions.assertEquals(afterInsertionLink.size(), 20);
        Assertions.assertEquals(userLinksBeforeRemove.size(), 10);
        Assertions.assertEquals(userLinksAfterRemove.size(), 7);
    }


    @Test
    @Transactional
    @Rollback
    public void addRelationTest() {
        List<Link> beforeAddLink = jdbcTemplate.query("select * from link inner join user_link rel on link.id = rel.link_id where rel.chat_id = ?", linkRowMapper, 42L);

        jdbcTemplate.update("insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)", 42L, "robtop21", "Robert", "Polson");

        for (int i = 0; i < 20; i++) {
            Link linkToAdd = new Link();
            linkToAdd.setUrl("https://stackoverflow.com/questions/2336692/java-multiple-class-declarations-in-one-file" + i);
            linkToAdd.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            jdbcTemplate.update("insert into link (url, updated_at) values(?, ?)", linkToAdd.getUrl(), linkToAdd.getUpdatedAt());
        }

        List<Link> afterInsertionLink = jdbcTemplate.query("select * from link", linkRowMapper);

        for (int i = 0; i < 10; i++) {
            Relation relation = new Relation();
            relation.setLinkId(afterInsertionLink.get(i).getId());
            relation.setChatId(42L);
            subscriptionRepository.addRelation(relation);
        }


        List<Link> userLinks = jdbcTemplate.query("select * from link inner join user_link rel on link.id = rel.link_id where rel.chat_id = ?", linkRowMapper, 42L);

        Assertions.assertEquals(beforeAddLink.size(), 0);
        Assertions.assertEquals(afterInsertionLink.size(), 20);
        Assertions.assertEquals(userLinks.size(), 10);
    }


    @Test
    @Transactional
    @Rollback
    public void findChatsByLinkTest() {
        List<Relation> beforeAddRelation = jdbcTemplate.query("select * from user_link", subscriptionRowMapper);


        for (int i = 0; i < 10; i++) {
            User user = new User(42L + i, "robtop21" + i, "Robert", "Polson");

            jdbcTemplate.update("insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)",
                    user.getChatId(),
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName());
        }


        Link linkToAdd = new Link();
        linkToAdd.setUrl("https://stackoverflow.com/questions/2336692/java-multiple-class-declarations-in-one-file");
        linkToAdd.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        jdbcTemplate.update("insert into link (url, updated_at) values(?, ?)", linkToAdd.getUrl(), linkToAdd.getUpdatedAt());

        List<Link> afterInsertionLink = jdbcTemplate.query("select * from link", linkRowMapper);


        for (int i = 0; i < 7; i++) {
            Relation relation = new Relation();
            relation.setLinkId(afterInsertionLink.get(0).getId());
            relation.setChatId(42L + i);
            subscriptionRepository.addRelation(relation);
        }

        List<Relation> afterSubscribe = subscriptionRepository.findChatsByLink(afterInsertionLink.get(0).getId());

        Assertions.assertEquals(beforeAddRelation.size(), 0);
        Assertions.assertEquals(afterSubscribe.size(), 7);
    }

    @Test
    @Transactional
    @Rollback
    public void findSubscriptionTest() {
        List<Relation> beforeAddRelation = jdbcTemplate.query("select * from user_link", subscriptionRowMapper);

        jdbcTemplate.update("insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)", 42L, "robtop21", "Robert", "Polson");


        Link linkToAdd = new Link();
        linkToAdd.setUrl("https://stackoverflow.com/questions/2336692/java-multiple-class-declarations-in-one-file");
        linkToAdd.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        jdbcTemplate.update("insert into link (url, updated_at) values(?, ?)", linkToAdd.getUrl(), linkToAdd.getUpdatedAt());
        List<Link> afterInsertionLink = jdbcTemplate.query("select * from link", linkRowMapper);
        jdbcTemplate.update("insert into user_link (link_id, chat_id) values(?, ?)", afterInsertionLink.get(0).getId(), 42L);

        Relation relation = subscriptionRepository.findSubscription(afterInsertionLink.get(0).getId(), 42L);


        Assertions.assertEquals(beforeAddRelation.size(),0);
        Assertions.assertEquals(afterInsertionLink.size(),1);
        Assertions.assertNotNull(relation);
    }


    @Test
    @Transactional
    @Rollback
    public void removeAllByUserTest(){
        List<Relation> beforeAddRelation = jdbcTemplate.query("select * from user_link", subscriptionRowMapper);


        jdbcTemplate.update("insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)", 42L, "robtop21", "Robert", "Polson");
        jdbcTemplate.update("insert into \"user\" (chat_id, username, first_name, last_name) values(?, ?, ?, ?)", 50L, "alucard", "Will", "Smith");

        //всего добавим 20 ссылок
        for (int i = 0; i < 20; i++) {
            Link linkToAdd = new Link();
            linkToAdd.setUrl("https://stackoverflow.com/questions/2336692/java-multiple-class-declarations-in-one-file" + i);
            linkToAdd.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            jdbcTemplate.update("insert into link (url, updated_at) values(?, ?)", linkToAdd.getUrl(), linkToAdd.getUpdatedAt());
        }

        List<Link> afterInsertionLink = jdbcTemplate.query("select * from link", linkRowMapper);

        //из 20-ти ссылок подпишемся лишь на 10
        for (int i = 0; i < 10; i++) {
            jdbcTemplate.update("insert into user_link (link_id, chat_id) values(?, ?)", afterInsertionLink.get(i).getId(), 42L);
        }

        //на оставшиеся 10 подпишем другого пользователя
        for (int i = 10; i < 20; i++) {
            jdbcTemplate.update("insert into user_link (link_id, chat_id) values(?, ?)", afterInsertionLink.get(i).getId(), 50L);
        }

        List<Link> userLinksBeforeDelete = jdbcTemplate.query("select * from link inner join user_link rel on link.id = rel.link_id where rel.chat_id = ?", linkRowMapper, 42L);

        subscriptionRepository.removeAllByUser(42L);

        List<Link> userLinksAfterDelete = jdbcTemplate.query("select * from link inner join user_link rel on link.id = rel.link_id where rel.chat_id = ?", linkRowMapper, 42L);

        Assertions.assertEquals(beforeAddRelation.size(),0);
        Assertions.assertEquals(afterInsertionLink.size(), 20);
        Assertions.assertEquals(userLinksBeforeDelete.size(), 10);
        Assertions.assertEquals(userLinksAfterDelete.size(), 0);
    }

}

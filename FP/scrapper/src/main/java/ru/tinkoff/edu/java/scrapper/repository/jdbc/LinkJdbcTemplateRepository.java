package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.mapper.LinkRowMapper;
import ru.tinkoff.edu.java.scrapper.model.commonDto.Link;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
public class LinkJdbcTemplateRepository implements LinkRepository {


    private final JdbcTemplate jdbcTemplate;

    private final LinkRowMapper linkRowMapper;

    public LinkJdbcTemplateRepository(JdbcTemplate jdbcTemplate, LinkRowMapper linkRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.linkRowMapper = linkRowMapper;
    }


    @Override
    public List<Link> findAll() {
        log.info("findAll() method invocation in linkRepo");
        String sql = "select * from link";
        return jdbcTemplate.query(sql, linkRowMapper);
    }

    @Override
    public Link findByUrl(String url) {
        log.info("findByUrl() method invocation in linkRepo");
        String sql = "select * from link where link.url = ?";
        List<Link> link = jdbcTemplate.query(sql, linkRowMapper, url);
        return link.size() == 0 ? null : link.get(0);
    }

    @Override
    public void add(Link link) {
        log.info("add() method invocation in linkRepo");
        String sql = "insert into link (url, updated_at) values(?, ?)";
        jdbcTemplate.update(sql, link.getUrl(), link.getUpdatedAt());
    }


    @Override
    public void updateDate(Link link) {
        log.info("updateDate() method invocation in linkRepo");
        String sql = "update link set updated_at = ? where id = ?";
        jdbcTemplate.update(sql, link.getUpdatedAt(), link.getId());
    }

    @Override
    public void remove(Long id) {
        log.info("remove() method invocation in linkRepo");
        String sql = "delete from link where link.id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    //поиск ссылок по критерию
    public List<Link> findOldLinks(Long timeUpdateDelta) {
        log.info("findOldLinks() method invocation in linkRepo");
        Timestamp compareDate = new Timestamp(System.currentTimeMillis() - timeUpdateDelta*1000);
        String sql = "select * from link where link.updated_at < ? order by link.updated_at desc";
        return jdbcTemplate.query(sql,linkRowMapper,compareDate);
    }
    @Override
    public void updateGhLink(Link link) {
        log.info("updateGhLink() method invocation in linkJdbcRepo");
        String sql = "update link set gh_forks_count = ?, gh_description = ?, gh_pushed_at = ? where id = ?";
        jdbcTemplate.update(sql, link.getGhForksCount(), link.getGhDescription(), link.getGhPushedAt(), link.getId());
    }

    @Override
    public void updateSoLink(Link link) {
        log.info("updateSoLastEditDate() method invocation in linkJdbcRepo");
        String sql = "update link set so_last_edit_date = ?, so_answer_count = ? where id = ?";
        jdbcTemplate.update(sql, link.getSoLastEditDate(), link.getSoAnswerCount(), link.getId());
    }

}
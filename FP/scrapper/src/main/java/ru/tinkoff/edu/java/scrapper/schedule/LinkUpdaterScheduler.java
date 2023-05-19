package ru.tinkoff.edu.java.scrapper.schedule;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.tinkoff.edu.java.linkparser.LinkParser;
import ru.tinkoff.edu.java.scrapper.client.ClientConfiguration;
import ru.tinkoff.edu.java.scrapper.jdbc.JdbcLinkService;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkOperations;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LinkUpdaterScheduler implements LinkOperations {
//    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//
//    private final JdbcTemplate jdbcTemplate;
//    ArrayList<String> link_list = new ArrayList<>();
//    static int ix = 0;
//    ClientConfiguration client = new ClientConfiguration();
//    LinkParser pars = new LinkParser();
//    boolean git_link_is_activity;
//    boolean stack_link_is_activity;
//
//    public LinkUpdaterScheduler(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
//    public void update() {
//        int i;
//        for (i = 0; i < i_getAllIds(jdbcTemplate).size(); i++){
//            if (!link_list.contains(i_getAllIds(jdbcTemplate).get(i).url())) {
//                link_list.add(i_getAllIds(jdbcTemplate).get(i).url());
//            }
//        }
//        try {
//            client.gitHubClient(pars.getLink(link_list.get(ix)));
//            git_link_is_activity = true;
//        } catch (WebClientResponseException e) {
//            git_link_is_activity = false;
//        }
//        try {
//            client.stackOverflowClient(pars.getLink(link_list.get(ix)));
//            stack_link_is_activity = true;
//        } catch (WebClientResponseException ignored) {
//            stack_link_is_activity = false;
//        }
//        if (!(git_link_is_activity || stack_link_is_activity)) {
//            String link = "Ссылка %s устарела";
//            link = link.formatted(i_getAllIds(jdbcTemplate).get(ix).url());
//            LOGGER.log(Level.INFO, link);
//        }
//        ix++;
//        if (ix == link_list.size()) ix = 0;
//    }
}

package ru.tinkoff.edu.java.scrapper.api;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.jdbc.JdbcChatService;
import ru.tinkoff.edu.java.scrapper.jdbc.JdbcLinkService;

@RequestMapping("/tg-chat")
@RestController
public class ScrapperControllerTg {

    private final JdbcTemplate jdbcTemplate;
    JdbcChatService chatService = new JdbcChatService();
    public ScrapperControllerTg(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void tgChatIdDelete(@PathVariable Long id) {
        chatService.removeChat(jdbcTemplate, id);
    }

    @PostMapping("/{id}")
    public void tgChatIdPost(@PathVariable long id) {
        chatService.addChat(jdbcTemplate, id);
    }
}

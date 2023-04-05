package ru.tinkoff.edu.java.scrapper.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tg-chat")
@RestController
public class ScrapperControllerTg {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void tgChatIdDelete(@PathVariable Long id) {
        DB.addId(id);
    }

    @PostMapping("/{id}")
    public void tgChatIdPost(@PathVariable Long id) {
        DB.rmId(id);
    }
}

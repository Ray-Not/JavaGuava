package ru.tinkoff.edu.java.scrapper.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tg-chat")
@RestController
public class ScrapperControllerTg {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void tgChatIdDelete(@PathVariable Long id) {}

    @PostMapping("/{id}")
    public String tgChatIdPost(@PathVariable Long id) {
        if(id < 0) return "Аргумент отрицательный";
        return id + "";
    }
}

package ru.tinkoff.edu.java.bot.api;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.bot.model.LinkUpdate;

@RestController
@RequestMapping("/update")
public class BotController {

    @PostMapping
    public String updateChat(@RequestBody LinkUpdate update) {
        return update.toString();
    }
}

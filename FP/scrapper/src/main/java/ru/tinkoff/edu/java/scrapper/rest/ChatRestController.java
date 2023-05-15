package ru.tinkoff.edu.java.scrapper.rest;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.service.contract.TgChatService;
import ru.tinkoff.edu.java.scrapper.dto.UserAddDto;
import ru.tinkoff.edu.java.scrapper.model.commonDto.User;

@RestController
@RequestMapping("/tg-chat")
public class ChatRestController {


    private final TgChatService chatService;

    public ChatRestController(TgChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "{id}")
    public void registerChat(@PathVariable Long id, @RequestBody UserAddDto userAddDto) {
        chatService.register(new User(id, userAddDto.username(), userAddDto.firstName(), userAddDto.lastName()));
    }

    @DeleteMapping(value = "{id}")
    public void deleteChat(@PathVariable Long id) {
        chatService.unregister(id);
    }


}
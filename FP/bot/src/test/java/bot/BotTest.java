package bot;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.commands.Command;
import ru.tinkoff.edu.java.bot.commands.CommandsEnum;
import ru.tinkoff.edu.java.bot.commands.ListCommand;
import ru.tinkoff.edu.java.bot.dto.Link;
import ru.tinkoff.edu.java.bot.dto.ListLinkResponse;
import ru.tinkoff.edu.java.bot.telegram.UserMessageProcessor;
import ru.tinkoff.edu.java.bot.telegram.UserState;

import java.net.URI;
import java.util.*;

public class BotTest {

    static UserMessageProcessor userMessageProcessor;

    static ScrapperClient scrapperClient;

    static Update updateForListCommand;
    static Update updateForInvalidCommand;
    static Message messageForListCommand;
    static Message messageForInvalidCommand;

    static Chat chat;

    @BeforeAll
    static void init() {
        //инициализируем заглушки и входные дынные, т.к. у Update в нет сеттеров, то используем рефлексию,
        //чтобы поместить туда нужный Message
        scrapperClient = Mockito.mock(ScrapperClient.class);
        updateForListCommand = new Update();
        messageForListCommand = new Message();
        chat = new Chat();
        ReflectionTestUtils.setField(chat, "id", 42L);
        ReflectionTestUtils.setField(messageForListCommand, "chat", chat);
        ReflectionTestUtils.setField(messageForListCommand, "text", "/list");
        ReflectionTestUtils.setField(updateForListCommand, "message", messageForListCommand);

        updateForInvalidCommand = new Update();
        messageForInvalidCommand = new Message();

        ReflectionTestUtils.setField(messageForInvalidCommand, "chat", chat);
        ReflectionTestUtils.setField(messageForInvalidCommand, "text", "Azazelo");
        ReflectionTestUtils.setField(updateForInvalidCommand, "message", messageForInvalidCommand);
    }

    @Test
    @DisplayName("Тест команды /list, когда список ссылок непустой")
    public void listCommandTestNotEmpty() {

        //имитируем нужное поведение клиента для Scrapper, т.к. реального запроса на Scrapper во время не происходит
        List<Link> listLink = new ArrayList<>();
        listLink.add(new Link(1L, URI.create("https://github.com/lwbeamer/asm-like-language")));
        listLink.add(new Link(2L, URI.create("https://stackoverflow.com/questions/512877/why-cant-i-define-a-static-method-in-a-java-interface")));
        Mockito.when(scrapperClient.getLinks(42L)).thenReturn(
                new ListLinkResponse(listLink, listLink.size())
        );

        EnumMap<CommandsEnum, Command> map = new EnumMap<>(CommandsEnum.class);
        map.put(CommandsEnum.LIST, new ListCommand(scrapperClient));

        userMessageProcessor = new UserMessageProcessor(map);

        String expectedMessage = """
                Ссылок отслеживается - 2

                https://github.com/lwbeamer/asm-like-language

                https://stackoverflow.com/questions/512877/why-cant-i-define-a-static-method-in-a-java-interface

                """;

        Assertions.assertEquals(expectedMessage, userMessageProcessor.process(updateForListCommand));
    }


    @Test
    @DisplayName("Тест команды /list, когда список ссылок пустой")
    public void listCommandTestEmpty() {
        Mockito.when(scrapperClient.getLinks(42L)).thenReturn(
                new ListLinkResponse(new ArrayList<>(), 0)
        );


        EnumMap<CommandsEnum, Command> map = new EnumMap<>(CommandsEnum.class);
        map.put(CommandsEnum.LIST, new ListCommand(scrapperClient));

        userMessageProcessor = new UserMessageProcessor(map);


        String expectedMessage = "Список отслеживаемых ссылок пуст!";

        Assertions.assertEquals(expectedMessage, userMessageProcessor.process(updateForListCommand));
    }

    @Test
    @DisplayName("Тест для проверки ввода неизвестной команды")
    public void invalidCommandCheck() {
        userMessageProcessor = new UserMessageProcessor(new EnumMap<>(CommandsEnum.class));

        //Устанавливаем состояние перед тестом - пользователь в сосвтоянии "печатает команду"
        Map<Long, UserState> userStateMap = new HashMap<>();
        userStateMap.put(chat.id(), UserState.TYPING_COMMAND);
        ReflectionTestUtils.setField(userMessageProcessor, "userStateMap", userStateMap);

        String expectedMessage = "Неизвестная команда. Нажмите 'Меню' чтобы посмотреть список доступных команд";

        Assertions.assertEquals(expectedMessage, userMessageProcessor.process(updateForInvalidCommand));

    }

}
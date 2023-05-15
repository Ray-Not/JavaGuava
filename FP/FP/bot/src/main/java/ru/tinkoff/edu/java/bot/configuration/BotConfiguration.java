package ru.tinkoff.edu.java.bot.configuration;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.commands.Command;
import ru.tinkoff.edu.java.bot.commands.CommandsEnum;
import ru.tinkoff.edu.java.bot.commands.HelpCommand;
import ru.tinkoff.edu.java.bot.commands.ListCommand;
import ru.tinkoff.edu.java.bot.commands.StartCommand;
import ru.tinkoff.edu.java.bot.commands.TrackCommand;
import ru.tinkoff.edu.java.bot.commands.UntrackCommand;
import ru.tinkoff.edu.java.bot.dto.BotCommand;
import ru.tinkoff.edu.java.bot.dto.SetCommandRequest;
import ru.tinkoff.edu.java.bot.telegram.Bot;

@Configuration
@Slf4j
public class BotConfiguration {

    @Value("${tg.bot.token}")
    private String token;

    @Value("${tg.api.baseUrl}")
    private String tgApiBaseUrl;

    private final HelpCommand helpCommand;
    private final StartCommand startCommand;
    private final ListCommand listCommand;
    private final TrackCommand trackCommand;
    private final UntrackCommand untrackCommand;

    public BotConfiguration(
        HelpCommand helpCommand,
        StartCommand startCommand,
        ListCommand listCommand,
        TrackCommand trackCommand,
        UntrackCommand untrackCommand
    ) {
        this.helpCommand = helpCommand;
        this.startCommand = startCommand;
        this.listCommand = listCommand;
        this.trackCommand = trackCommand;
        this.untrackCommand = untrackCommand;
    }

    @Bean
    public Bot bot() {
        EnumMap<CommandsEnum, Command> commands = new EnumMap<>(CommandsEnum.class);

        commands.put(CommandsEnum.HELP, helpCommand);
        commands.put(CommandsEnum.LIST, listCommand);
        commands.put(CommandsEnum.START, startCommand);
        commands.put(CommandsEnum.TRACK, trackCommand);
        commands.put(CommandsEnum.UNTRACK, untrackCommand);

        //Делаем кнопку "Меню" рядом с иконкой скрепки
        //нужно отправить запрос на API телеграмма (метод setMyCommands) со списком команд в виде List<BotCommand>
        List<BotCommand> apiCommands = new ArrayList<>(commands.values().stream().map(Command::toApiCommand).toList());
        WebClient botConfClient = WebClient.create(tgApiBaseUrl + token);
        botConfClient.post().uri("/setMyCommands").bodyValue(new SetCommandRequest(apiCommands)).exchangeToMono(r -> {
            if (!r.statusCode().equals(HttpStatus.OK)) {
                log.warn("Похоже, что API Telegram недоступен(");
            }
            return Mono.empty();
        }).block();

        return new Bot(token, commands);
    }

}
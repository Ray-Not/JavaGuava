package ru.tinkoff.edu.java.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record SetCommandRequest(@JsonProperty("commands") List<BotCommand> commands) {
}
package ru.tinkoff.edu.java.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public record BotCommand(@JsonProperty("command") String command, @JsonProperty("description") String description) implements Serializable {

}
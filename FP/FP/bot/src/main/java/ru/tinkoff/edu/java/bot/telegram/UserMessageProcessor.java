package ru.tinkoff.edu.java.bot.telegram;

import com.pengrad.telegrambot.model.Update;
import ru.tinkoff.edu.java.bot.commands.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class UserMessageProcessor {

//      private final List<Command> commands;

      // Состояние пользователя. Нужно для удобной работы с командами /track и /untrack
      // TYPING_COMMAND - состояние ввода команды (по-умолчанию)
      // TYPING_TRACKED - состояние ввода ссылки для добавления
      // TYPING_UNTRACKED - состояние ввода ссылки для удаления
      private final Map<Long, UserState> userStateMap;

      private final EnumMap<CommandsEnum, Command> commands;

      public UserMessageProcessor(EnumMap<CommandsEnum, Command> commands) {
          this.commands = commands;
          userStateMap = new HashMap<>();
      }

      public String process(Update update) {
          Command command;

          userStateMap.putIfAbsent(update.message().chat().id(), UserState.TYPING_COMMAND);
          switch (userStateMap.get(update.message().chat().id())) {
              case TYPING_TRACKED -> {
                  userStateMap.put(update.message().chat().id(), UserState.TYPING_COMMAND);
                  return commands.get(CommandsEnum.valueOfLabel("/track")).handle(update);
              }
              case TYPING_UNTRACKED -> {
                  userStateMap.put(update.message().chat().id(), UserState.TYPING_COMMAND);
                  return commands.get(CommandsEnum.valueOfLabel("/untrack")).handle(update);
              }
              case TYPING_COMMAND -> {
                  userStateMap.put(update.message().chat().id(), UserState.TYPING_COMMAND);
                  command = commands.get(CommandsEnum.valueOfLabel(update.message().text()));
                  if (command == null) {
                      return "Неизвестная команда. Нажмите 'Меню' чтобы посмотреть список доступных команд";
                  }
                  if (command instanceof TrackCommand) {
                      userStateMap.put(update.message().chat().id(), UserState.TYPING_TRACKED);
                      return "Отправьте ссылку, которую хотите начать отслеживать";
                  }
                  if (command instanceof UntrackCommand) {
                      userStateMap.put(update.message().chat().id(), UserState.TYPING_UNTRACKED);
                      return "Отправьте ссылку, которую хотите перестать отслеживать";
                  }
                  if (command instanceof HelpCommand) {
                      StringBuilder text = new StringBuilder();
                      for (Command c : commands.values()) {
                          text.append(c.command()).append(" - ").append(c.description()).append("\n");
                      }
                      return text.toString();
                  }
                  return command.handle(update);
              }
              default -> {
                  //По логике этот return никак недостижим
                  return "Что-то пошло не так. Проблема на нашей стороне";

              }
          }

      }

  }vate final Map<Long, UserState> userStateMap;

    private final EnumMap<CommandsEnum, Command> commands;


    public UserMessageProcessor(EnumMap<CommandsEnum, Command> commands) {
        this.commands = commands;
        userStateMap = new HashMap<>();
    }

    public String process(Update update) {
        Command command;


        userStateMap.putIfAbsent(update.message().chat().id(), UserState.TYPING_COMMAND);
        switch (userStateMap.get(update.message().chat().id())) {
            case TYPING_TRACKED -> {
                userStateMap.put(update.message().chat().id(), UserState.TYPING_COMMAND);
                return commands.get(CommandsEnum.valueOfLabel("/track")).handle(update);
            }
            case TYPING_UNTRACKED -> {
                userStateMap.put(update.message().chat().id(), UserState.TYPING_COMMAND);
                return commands.get(CommandsEnum.valueOfLabel("/untrack")).handle(update);
            }
            case TYPING_COMMAND -> {
                userStateMap.put(update.message().chat().id(), UserState.TYPING_COMMAND);
                command = commands.get(CommandsEnum.valueOfLabel(update.message().text()));
                if (command == null)
                    return "Неизвестная команда. Нажмите 'Меню' чтобы посмотреть список доступных команд";
                if (command instanceof TrackCommand) {
                    userStateMap.put(update.message().chat().id(), UserState.TYPING_TRACKED);
                    return "Отправьте ссылку, которую хотите начать отслеживать";
                }
                if (command instanceof UntrackCommand) {
                    userStateMap.put(update.message().chat().id(), UserState.TYPING_UNTRACKED);
                    return "Отправьте ссылку, которую хотите перестать отслеживать";
                }
                if (command instanceof HelpCommand){
                    StringBuilder text = new StringBuilder();
                    for (Command c : commands.values()) {
                        text.append(c.command()).append(" - ").append(c.description()).append("\n");
                    }
                    return text.toString();
                }
                return command.handle(update);
            }
            default -> {
            	
                return "Что-то пошло не так. Проблема на нашеё стороне";
            }
        }

    }


}
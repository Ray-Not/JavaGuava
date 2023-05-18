package ru.tinkoff.edu.java.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.bot.configuration.records.ApplicationConfig;
import ru.tinkoff.edu.java.bot.handler.BotMain;


@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication
{
        public static void main(String[] args){
                var ctx = SpringApplication.run(BotApplication.class, args);
                ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
                BotMain bot = new BotMain(config.bot().token());
                bot.start();
        }
}

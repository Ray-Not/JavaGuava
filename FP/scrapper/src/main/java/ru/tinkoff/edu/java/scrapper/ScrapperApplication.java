package ru.tinkoff.edu.java.scrapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.rabbitmq.ScrapperQueueProducer;
import ru.tinkoff.edu.java.scrapper.rabbitmq.SendNoticeServiceQueue;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ScrapperApplication {

        public static void main(String[] args) {
        var ctx = SpringApplication.run(ScrapperApplication.class, args);
        ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
        System.out.println("----------------------------------------------------------------");
//        SendNoticeServiceQueue notificationService = new SendNoticeServiceQueue(new ScrapperQueueProducer(
//                new RabbitTemplate(),
//                config
//        ));
//        notificationService.sendNotice("Вот-вот");
       }
}

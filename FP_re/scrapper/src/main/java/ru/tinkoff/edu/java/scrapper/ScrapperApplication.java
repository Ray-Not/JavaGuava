package ru.tinkoff.edu.java.scrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.scrapper.client.ClientConfiguration;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.linkHandler.ParamsConverter;

import java.util.HashMap;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class ScrapperApplication {
public static void main(String[] args) {
        var ctx = SpringApplication.run(ScrapperApplication.class, args);
        ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
        System.out.println("----------------------------------------------------------------");
        ClientConfiguration cls = ctx.getBean(ClientConfiguration.class);
//        System.out.println(cls.weatherClient());
        System.out.println(cls.gitHubClient());
        System.out.println(cls.stackOverflowClient());
//        HashMap trt = new HashMap();
//        trt.put("order", "desc");
//        trt.put("sort", "activity");
//        trt.put("site", "stackoverflow");
//        System.out.println(ParamsConverter.setParamsLink("https://api.stackexchange.com/2.3/questions/552659", trt));
        }
}

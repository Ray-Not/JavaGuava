import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.link_parser.parser.*;
import ru.tinkoff.edu.java.link_parser.link.*;

public class LinkParserTest {

    static String validGitHubLink;
    static String validStackOverflowLink;
    static String gitHubNotRepoLink;
    static String stackOverflowNotQuestionLink;
    static String emptyLink;
    static String withoutProtocolLink;
    static String unknownHostLink;
    static String invalidLink;


    @BeforeAll
    static void init() {
        validGitHubLink = "https://github.com/lwbeamer/asm-like-language";
        validStackOverflowLink = "https://stackoverflow.com/questions/2336692/java-multiple-class-declarations-in-one-file";
        gitHubNotRepoLink = "https://github.com/issues";
        stackOverflowNotQuestionLink = "https://stackoverflow.co/talent";
        emptyLink = "";
        withoutProtocolLink = "github.com/lwbeamer/asm-like-language";
        unknownHostLink = "https://vk.com/feed";
        invalidLink = "somethingNotValid";
    }


    @Test
    @DisplayName("Тест для проверки валидной ссылки с GitHub")
    void checkValidGitHubLink() {
        Link_Parser parser = new Link_Parser();
        Assertions.assertEquals(new GitHub_Link("lwbeamer", "asm-like-language"), parser.parseUrl(validGitHubLink), "Тест не пройден, проверьте реузультат");
    }


    @Test
    @DisplayName("Тест для проверки валидной ссылки со StackOverflow")
    void validStackOverflowLink() {
        Link_Parser parser = new Link_Parser();
        Assertions.assertEquals(new StackOverflow_Link(2336692), parser.parseUrl(validStackOverflowLink), "Тест не пройден, проверьте реузультат");
    }


    @Test
    @DisplayName("Тест для проверки ссылки с GitHub, не являющейся репозиторием")
    void gitHubNotRepoLink() {
        Link_Parser parser = new Link_Parser();
        Assertions.assertNull(parser.parseUrl(gitHubNotRepoLink), "Тест не пройден, проверьте реузультат");
    }

    @Test
    @DisplayName("Тест для проверки ссылки со StackOverflow, не являющейся вопросом")
    void stackOverflowNotQuestionLink() {
        Link_Parser parser = new Link_Parser();
        Assertions.assertNull(parser.parseUrl(stackOverflowNotQuestionLink), "Тест не пройден, проверьте реузультат");
    }


    @Test
    @DisplayName("Тест для проверки пустой ссылки")
    void emptyLink() {
        Link_Parser parser = new Link_Parser();
        Assertions.assertNull(parser.parseUrl(emptyLink), "Тест не пройден, проверьте реузультат");
    }


    @Test
    @DisplayName("Тест для проверки ссылки без указания протокола")
    void withoutProtocolLink() {
        Link_Parser parser = new Link_Parser();
        Assertions.assertNull(parser.parseUrl(withoutProtocolLink), "Тест не пройден, проверьте реузультат");
    }

    @Test
    @DisplayName("Тест для проверки ссылки, не относящейся к отслеживаемым ресурсам")
    void unknownHostLink() {
        Link_Parser parser = new Link_Parser();
        Assertions.assertNull(parser.parseUrl(unknownHostLink), "Тест не пройден, проверьте реузультат");
    }


    @Test
    @DisplayName("Тест для проверки непустой невалидной ссылки")
    void invalidLink() {
        Link_Parser parser = new Link_Parser();
        Assertions.assertNull(parser.parseUrl(invalidLink), "Тест не пройден, проверьте реузультат");
    }


}

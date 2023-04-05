import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.bot.firstBot.DB;
import ru.tinkoff.edu.java.bot.firstBot.commands.List;


public class BotTest implements List {

    String except_empty_list = "list пустой";

    @Test
    public void listTest() {
        Assertions.assertEquals(list(), except_empty_list);
        DB.addLink("link");
        Assertions.assertNotEquals(list(), except_empty_list);
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.bot.handler.DB;
import ru.tinkoff.edu.java.bot.handler.MessageHandler;


public class BotTest {

    String except_empty_list = "list пустой";
    String except_unknow_command = "<b>Неизвестная команда</b>";
    MessageHandler handler = new MessageHandler();

    @Test
    public void listTest() {
        Assertions.assertEquals(handler.call_command("/list"), except_empty_list);
        DB.addLink("link");
        Assertions.assertNotEquals(handler.call_command("/list"), except_empty_list);
    }

    @Test
    public void unknowCommandAndFormatTest() {
        Assertions.assertEquals(handler.call_command("/uno"), except_unknow_command);
    }
}

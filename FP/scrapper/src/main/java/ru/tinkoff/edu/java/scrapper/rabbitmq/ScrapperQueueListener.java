import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.tinkoff.edu.java.bot.dto.LinkUpdate;

@RabbitListener(queues = "${app.queue-name}")
@Slf4j
public class ScrapperQueueListener {


    private final AmqpTemplate rabbitTemplate;

    private final UpdateService updateService;


    public ScrapperQueueListener(AmqpTemplate rabbitTemplate, UpdateService updateService) {
        this.rabbitTemplate = rabbitTemplate;
        this.updateService = updateService;
    }

    @RabbitHandler
    public void receiver(LinkUpdate update) {
        log.info("Сообщение об обновлении получено: "+update);
//        throw new RuntimeException("test exception");
        updateService.updateLink(update);
    }


}
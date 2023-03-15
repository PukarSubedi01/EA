package kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReceiverTopicA2 {
    @KafkaListener(topics = {"topic2A"})
    public void receive(@Payload String message) {
        System.out.println("ReceiverTopicA2 received message= "+ message);
    }
}

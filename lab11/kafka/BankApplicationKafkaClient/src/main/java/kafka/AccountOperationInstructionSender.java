package kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountOperationInstructionSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    private final String accountOperationTopic = "account-operation";

    public void send(AccountOperationInstruction operationInstruction) throws JsonProcessingException {
        String operationInstructionAsString = objectMapper.writeValueAsString(operationInstruction);
        System.out.println("Sending operation instruction: " + operationInstructionAsString);
        kafkaTemplate.send(accountOperationTopic, operationInstructionAsString);
    }
}

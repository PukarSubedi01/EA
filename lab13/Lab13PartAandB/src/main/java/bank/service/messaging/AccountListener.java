package bank.service.messaging;

import bank.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccountListener {
    Logger logger = LoggerFactory.getLogger(AccountListener.class);

    @Autowired
    AccountService accountService;

    @KafkaListener(topics = {"accountTopic"})
    public void handleMessage(String instructionAsString) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            AccountInstruction instruction = objectMapper.readValue(instructionAsString, AccountInstruction.class);
            logger.info("Kafka receiver received message:" + instruction);
            switch (instruction.getAction()) {
                case "createAccount":
                    accountService.createAccount(instruction.getAccountNumber(), instruction.getCustomerName());
                    break;
                case "deposit":
                    accountService.deposit(instruction.getAccountNumber(), instruction.getAmount());
                    break;
                case "withdraw":
                    accountService.withdraw(instruction.getAccountNumber(), instruction.getAmount());
                    break;
            }
        } catch (IOException e) {
            logger.info("Kafka receiver: Cannot convert : " + instructionAsString + " to a AccountInstruction object");
        }
    }
}


package bank.service.kafka;


import bank.logging.ILogger;
import bank.service.IAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AccountOperationListener {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ILogger logger;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "account-operation")
    public void receiveOperation(String accountOperationInstructionMessage) throws JsonProcessingException {
        logger.log("Received account operation instruction: {}" + accountOperationInstructionMessage);
        AccountOperationInstruction accountOperationInstruction = objectMapper.readValue(accountOperationInstructionMessage, AccountOperationInstruction.class);
        switch (accountOperationInstruction.getOperation()) {
            case "create":
                accountService.createAccount(accountOperationInstruction.getAccountNumber(), accountOperationInstruction.getCustomerName());
                break;
            case "deposit":
                accountService.deposit(accountOperationInstruction.getAccountNumber(), accountOperationInstruction.getAmount());
                break;
            case "withdraw":
                accountService.withdraw(accountOperationInstruction.getAccountNumber(), accountOperationInstruction.getAmount());
                break;
            default:
                logger.log("Kafka Listener: Invalid operation: {}" + accountOperationInstruction);
        }
    }
}

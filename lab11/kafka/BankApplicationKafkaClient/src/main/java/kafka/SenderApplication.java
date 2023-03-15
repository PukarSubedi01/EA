package kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SenderApplication implements CommandLineRunner {
    @Autowired
    private AccountOperationInstructionSender sender;

    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n--------------------------------------------------------------------------------------");
        System.out.println("    Creating account");
        System.out.println("--------------------------------------------------------------------------------------");
        AccountOperationInstruction accountOperationInstruction1 = new AccountOperationInstruction();
        accountOperationInstruction1.setOperation("create");
        accountOperationInstruction1.setAccountNumber(12312345);
        accountOperationInstruction1.setCustomerName("Harry Potter");
        sender.send(accountOperationInstruction1);
        System.out.println("Message has been sent: {}" + accountOperationInstruction1);
        System.out.println("-------------------------------------DONE---------------------------------------------");

        System.out.println("\n--------------------------------------------------------------------------------------");
        System.out.println("    Depositing to created account");
        System.out.println("--------------------------------------------------------------------------------------");
        AccountOperationInstruction accountOperationInstruction2 = new AccountOperationInstruction();
        accountOperationInstruction2.setOperation("deposit");
        accountOperationInstruction2.setAccountNumber(12312345);
        accountOperationInstruction2.setAmount(12300);
        sender.send(accountOperationInstruction2);
        System.out.println("Message has been sent: {}" + accountOperationInstruction2);
        System.out.println("-------------------------------------DONE---------------------------------------------");

        System.out.println("\n--------------------------------------------------------------------------------------");
        System.out.println("    Withdrawing from created account");
        System.out.println("--------------------------------------------------------------------------------------");
        AccountOperationInstruction accountOperationInstruction3 = new AccountOperationInstruction();
        accountOperationInstruction3.setOperation("withdraw");
        accountOperationInstruction3.setAccountNumber(12312345);
        accountOperationInstruction3.setAmount(120);
        sender.send(accountOperationInstruction3);
        System.out.println("Message has been sent: {}" + accountOperationInstruction3);
        System.out.println("-------------------------------------DONE---------------------------------------------");

    }
}

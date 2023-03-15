package bank.service.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountOperationInstruction {
    private String operation;
    private long accountNumber;
    private double amount;
    private String customerName;
}

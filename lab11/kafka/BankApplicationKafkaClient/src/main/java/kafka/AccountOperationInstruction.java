package kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class AccountOperationInstruction implements Serializable {
    private String operation;
    private long accountNumber;
    private double amount;
    private String customerName;

}

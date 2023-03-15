package bank.service.dto.requestDto;

import lombok.Data;

@Data
public class AccountCreateRequest {
    private long accountNumber;
    private String customerName;
}

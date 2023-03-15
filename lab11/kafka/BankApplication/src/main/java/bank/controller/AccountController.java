package bank.controller;

import bank.service.IAccountService;
import bank.service.dto.requestDto.TransactionRequest;
import bank.service.dto.responseDto.AccountDto;
import bank.service.dto.requestDto.AccountCreateRequest;
import bank.service.dto.responseDto.CustomErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("accounts")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountCreateRequest accountRequestDto) {
        AccountDto accountDto = accountService.createAccount(accountRequestDto.getAccountNumber(), accountRequestDto.getCustomerName());
        return new ResponseEntity(accountDto, HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAccount(@PathVariable long accountNumber) {
        AccountDto accountDto = accountService.getAccount(accountNumber);
        return new ResponseEntity(accountDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        Collection<AccountDto> accountList = accountService.getAllAccounts();
        return new ResponseEntity(accountList, HttpStatus.OK);
    }

    @PostMapping("/{accountNumber}/transactions")
    public ResponseEntity<?> deposit(@PathVariable long accountNumber
            , @RequestParam(value="type") String transactionType
            , @RequestBody TransactionRequest transactionRequest) {
        switch (transactionType) {
            case "deposit":
                accountService.deposit(accountNumber, transactionRequest.getAmount());
                break;
            case "deposit-euros":
                accountService.depositEuros(accountNumber, transactionRequest.getAmount());
                break;
            case "withdraw":
                accountService.withdraw(accountNumber, transactionRequest.getAmount());
                break;
            case "withdraw-euros":
                accountService.withdrawEuros(accountNumber, transactionRequest.getAmount());
                break;
            case "transfer":
                accountService.transferFunds(accountNumber
                        , transactionRequest.getToAccountNumber()
                        , transactionRequest.getAmount()
                        , transactionRequest.getDescription());
                break;
            default:
                return new ResponseEntity<>(new CustomErrorDto("Invalid transaction type"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accountService.getAccount(accountNumber), HttpStatus.OK);
    }
}

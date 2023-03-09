package bank.controllers;

import bank.dto.AccountDTO;
import bank.model.NewAccount;
import bank.model.Transaction;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/accounts")
public class BankController {
    @Autowired
    private AccountService accountService;

//    add new account
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody NewAccount account) {
        AccountDTO accountDTO =accountService.createAccount(account.getAccountNumber(),account.getName());
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }
//    get all accounts
    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(),HttpStatus.OK);
    }
//    get an account
    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAnAccount(@PathVariable long accountNumber){
        return new ResponseEntity<>(accountService.getAccount(accountNumber),HttpStatus.OK);
    }
    @PostMapping("{accountNumber}/transactions")
    public ResponseEntity<?> performTransaction(@RequestParam String transactionType, @PathVariable long accountNumber, @RequestBody Transaction transaction){
        if (transactionType.equals("deposit")){
            accountService.deposit(accountNumber,transaction.getAmount());
        } else if (transactionType.equals("withdraw")) {
            accountService.withdraw(accountNumber,transaction.getAmount());
        } else if (transactionType.equals("depositEuros")) {
            accountService.depositEuros(accountNumber,transaction.getAmount());
        }else if (transactionType.equals("withdrawEuros")) {
            accountService.withdrawEuros(accountNumber,transaction.getAmount());
        }else if(transactionType.equals("transfer")){
            accountService.transferFunds(accountNumber,transaction.getToAccountNumber(),transaction.getAmount(),transaction.getDescription());
        }
        return new ResponseEntity<>(transactionType+" is done successfully",HttpStatus.CREATED);
    }
}


package gjin_bardhi.bank_application.controller;

import gjin_bardhi.bank_application.data.*;
import gjin_bardhi.bank_application.etity.Account;
import gjin_bardhi.bank_application.etity.Transactions;
import gjin_bardhi.bank_application.service.BankService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bank")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping("/create")
    private ResponseEntity<Long> createBank(@RequestBody @Valid CreateBank createBank){
        Long id = bankService.createBank(createBank);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/create/account")
    private ResponseEntity<Long> createAccount(@RequestBody @Valid CreateAccount createAccount){
        Long id = bankService.createAccount(createAccount);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/transaction")
    private ResponseEntity<Boolean> transaction(@RequestBody @Valid CreateTransaction createTransaction){
        Boolean isTrue =bankService.transaction(createTransaction);
        return ResponseEntity.ok(isTrue);
    }
    @PutMapping("/account/transaction")
    private ResponseEntity<Boolean> transaction(@RequestBody @Valid WithdrawDeposit withdrawDeposit){
        Boolean isTrue =bankService.accountWithdrawDeposit(withdrawDeposit);
        return ResponseEntity.ok(isTrue);
    }

    @GetMapping("/account/transactions/{accountId}")
    private ResponseEntity<List<Transactions>> accountTransactions(@PathVariable Long accountId){
        List<Transactions> transactions =bankService.accountTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/account/balance/{accountId}")
    private ResponseEntity<Double> accountBalance(@PathVariable Long accountId){
        Double balance =bankService.accountBalance(accountId);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/accounts/{bankId}")
    private ResponseEntity<List<Account>> accounts(@PathVariable Long bankId){
        return ResponseEntity.ok(bankService.accountsByBankId(bankId));
    }

    @GetMapping("/total/fee/{bankId}")
    private ResponseEntity<TotalFee> totalFee(@PathVariable Long bankId){
        return ResponseEntity.ok(bankService.totalFee(bankId));
    }

    @GetMapping("/total/transactions/{bankId}")
    private ResponseEntity<Double> totalTransactions(@PathVariable Long bankId){
        return ResponseEntity.ok(bankService.totalTransactions(bankId));
    }
}

package gjin_bardhi.bank_application.service;

import gjin_bardhi.bank_application.data.*;
import gjin_bardhi.bank_application.etity.Account;
import gjin_bardhi.bank_application.etity.Bank;
import gjin_bardhi.bank_application.etity.Transactions;
import gjin_bardhi.bank_application.repository.AccountRepository;
import gjin_bardhi.bank_application.repository.BankRepository;
import gjin_bardhi.bank_application.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Long createBank(CreateBank createBank){
        Bank bank = Bank.builder()
                .name(createBank.getName())
                .totalTransaction(createBank.getTotalTransaction())
                .totalTransactionFee(createBank.getTotalTransactionFee())
                .totalTransactionFlatFee(createBank.getTotalTransactionFlatFee())
                .build();

        bankRepository.save(bank);

        return bank.getId();

    }

    public Long createAccount(CreateAccount createAccount){
        Bank bank = bankRepository.findById(createAccount.getBankId()).orElseThrow(() ->new RuntimeException("bank not found"));
        Account account = Account.builder()
                .name(createAccount.getName())
                .amount(createAccount.getAmount())
                .bank(bank).build();

        accountRepository.save(account);

        return account.getId();
    }

    public Boolean transaction(CreateTransaction createTransaction){
        Account origin = accountRepository.findById(createTransaction.getOriginatingId()).orElseThrow(() -> new RuntimeException("account not found"));
        Account result = accountRepository.findById(createTransaction.getResultingId()).orElseThrow(() -> new RuntimeException("account not found"));
        Bank bank = bankRepository.findById(origin.getBank().getId()).orElseThrow(() ->new RuntimeException("bank not found"));
        Double fee ;
        if (createTransaction.getPercentageFee()){
            fee = createTransaction.getAmount() * 0.05;
            bank.setTotalTransactionFee(bank.getTotalTransactionFee()+ fee);
        } else if (createTransaction.getAmount() > 10){
            fee = 10.00;
            bank.setTotalTransactionFlatFee(bank.getTotalTransactionFlatFee()+ fee);
        } else throw new RuntimeException("insufficient funds");

        bank.setTotalTransaction(bank.getTotalTransaction()+ createTransaction.getAmount()- fee);
        origin.setAmount(origin.getAmount() - createTransaction.getAmount());
        result.setAmount(result.getAmount()+ createTransaction.getAmount() - fee);

        Transactions transactions = Transactions
                .builder()
                .originatingId(origin.getId())
                .resultingId(result.getId())
                .reason(createTransaction.getReason())
                .amount(createTransaction.getAmount())
                .build();

        transactionRepository.save(transactions);
        bankRepository.save(bank);
        accountRepository.save(origin);
        accountRepository.save(result);

        return true;
    }

    public Boolean accountWithdrawDeposit(WithdrawDeposit withdrawDeposit){
        Account account = accountRepository.findById(withdrawDeposit.getAccountId()).orElseThrow(() -> new RuntimeException("account not found"));
        if (withdrawDeposit.getDeposit())
            account.setAmount(account.getAmount()+ withdrawDeposit.getAmount());
        else{
            if (account.getAmount() <= withdrawDeposit.getAmount())
                account.setAmount(account.getAmount()- withdrawDeposit.getAmount());
            else
                throw new RuntimeException("insufficient funds");
        }

        accountRepository.save(account);
        return Boolean.TRUE;
    }

    public List<Transactions> accountTransactions(Long accountId){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("account not found"));
        return transactionRepository.findByAccount(accountId);
    }

    public Double accountBalance(Long accountId){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("account not found"));
        return account.getAmount();
    }

    public List<Account> accountsByBankId(Long bankId){
        Bank bank = bankRepository.findById(bankId).orElseThrow(() ->new RuntimeException("bank not found"));
        return accountRepository.findByBankId(bankId);
    }

    public TotalFee totalFee(Long bankId){
        Bank bank = bankRepository.findById(bankId).orElseThrow(() ->new RuntimeException("bank not found"));
        return TotalFee.builder().totalTransactionFee(bank.getTotalTransactionFee()).totalTransactionFlatFee(bank.getTotalTransactionFlatFee()).build();
    }

    public Double totalTransactions(Long bankId){
        Bank bank = bankRepository.findById(bankId).orElseThrow(() ->new RuntimeException("bank not found"));
        return bank.getTotalTransaction();
    }
}

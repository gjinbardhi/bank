package gjin_bardhi.bank_application.repository;

import gjin_bardhi.bank_application.etity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.bank.id = ?1")
    List<Account> findByBankId(Long bankId);

}

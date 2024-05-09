package gjin_bardhi.bank_application.repository;

import gjin_bardhi.bank_application.etity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}

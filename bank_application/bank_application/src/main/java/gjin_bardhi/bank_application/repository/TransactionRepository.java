package gjin_bardhi.bank_application.repository;

import gjin_bardhi.bank_application.etity.Transactions;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    @Query("SELECT t FROM Transactions t WHERE t.originatingId = ?1 OR t.resultingId = ?1")
    List<Transactions> findByAccount(Long account);
}

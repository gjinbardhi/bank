package gjin_bardhi.bank_application.etity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bank extends BaseEntity{
    @Column(name = "bank_name")
    private String name;

    @OneToMany(mappedBy = "bank")
    private List<Account> accountList;

    @Column(name = "total_transaction_fee")
    private Double totalTransactionFee;

    @Column(name = "total_transaction_flat_fee")
    private Double totalTransactionFlatFee;

    @Column(name = "total_transaction")
    private Double totalTransaction;
}

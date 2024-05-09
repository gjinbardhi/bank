package gjin_bardhi.bank_application.etity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transactions extends BaseEntity {

    @Column(name = "amount")
    private Double amount;

    @Column(name = "originating_account_id")
    private Long originatingId;

    @Column(name = "resulting_account_id")
    private Long resultingId;

    @Column(name = "reason")
    private String reason;
}

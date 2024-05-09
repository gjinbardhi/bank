package gjin_bardhi.bank_application.data;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalFee {

    private Double totalTransactionFee;
    private Double totalTransactionFlatFee;

}

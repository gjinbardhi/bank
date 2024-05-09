package gjin_bardhi.bank_application.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawDeposit {

    @Min(value = 0, message = "value must be greater than 0")
    private Double amount;

    @NotNull(message = "must not be null")
    private Long accountId;

    @NotNull(message = "must not be null")
    private Boolean deposit;
}

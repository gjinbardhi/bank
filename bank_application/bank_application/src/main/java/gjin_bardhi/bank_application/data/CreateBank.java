package gjin_bardhi.bank_application.data;

import gjin_bardhi.bank_application.etity.Account;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBank {
    @NotBlank(message = "must not be blank")
    private String name;

    @Min(value = 0, message = "value must be greater than 0")
    private Double totalTransactionFee;

    @Min(value = 0, message = "value must be greater than 0")
    private Double totalTransactionFlatFee;

    @Min(value = 0, message = "value must be greater than 0")
    private Double totalTransaction;
}

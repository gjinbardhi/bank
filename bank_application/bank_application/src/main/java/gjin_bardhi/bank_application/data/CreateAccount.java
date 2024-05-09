package gjin_bardhi.bank_application.data;

import gjin_bardhi.bank_application.etity.Bank;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccount {
    @NotBlank(message = "must not be blank")
    private String name;

    @Min(value = 0, message = "value must be greater than 0")
    private Double amount;

    @NotNull(message = "must not be null")
    private Long bankId;
}

package gjin_bardhi.bank_application.data;

import jakarta.persistence.Column;
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
public class CreateTransaction {

    @Min(value = 0, message = "value must be greater than 0")
    private Double amount;
    @NotNull(message = "must not be null")
    private Long originatingId;
    @NotNull(message = "must not be null")
    private Long resultingId;
    @NotBlank(message = "must not be blank")
    private String reason;
    @NotNull(message = "must not be null")
    private Boolean percentageFee;
}

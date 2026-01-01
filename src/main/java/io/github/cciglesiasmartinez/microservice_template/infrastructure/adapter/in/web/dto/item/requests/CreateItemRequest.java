package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests;

import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.Condition;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateItemRequest {

    @NotBlank(message = "Edition ID is required")
    private String editionId;

    @PastOrPresent(message = "Purchase date cannot be in the future")
    private LocalDate purchaseDate;

    @Size(max = 255, message = "Purchase place must not exceed 255 characters")
    private String purchasePlace;

    @Positive(message = "Purchase price must be positive")
    @DecimalMin(value = "0.01", message = "Purchase price must be at least 0.01")
    private Double purchasePrice;

    @NotNull(message = "Media condition is required")
    private Condition mediaCondition;

    @NotNull(message = "Case condition is required")
    private Condition caseCondition;

    @Size(max = 5000, message = "Comments must not exceed 5000 characters")
    private String comments;


}

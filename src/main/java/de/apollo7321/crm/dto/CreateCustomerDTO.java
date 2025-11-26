package de.apollo7321.crm.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerDTO(
        @NotBlank String firstName,
        @NotBlank String lastName
) {
}

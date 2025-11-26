package de.apollo7321.crm.dto;

import jakarta.validation.Validation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateCustomerDTOTest {

    @ParameterizedTest
    @CsvSource({"Max,Mustermann,0", ",Mustermann,1", "Max,,1", ",,2"})
    void validateCreateCustomerDTO_shouldFailForBlankFields(String firstName, String lastName, int violationCount) {
        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var violations = validator.validate(new CreateCustomerDTO(firstName, lastName));
        assertEquals(violationCount, violations.size());
    }
}
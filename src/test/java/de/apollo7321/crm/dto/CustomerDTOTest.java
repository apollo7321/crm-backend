package de.apollo7321.crm.dto;

import de.apollo7321.crm.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerDTOTest {

    @Test
    void fromCustomer_shouldMapFields() {
        var customer = new Customer("Max", "Mustermann");
        var dto = new CustomerDTO(customer);
        assertEquals("Max", dto.firstName());
        assertEquals("Mustermann", dto.lastName());
    }
}

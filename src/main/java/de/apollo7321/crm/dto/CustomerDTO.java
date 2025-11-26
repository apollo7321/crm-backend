package de.apollo7321.crm.dto;

import de.apollo7321.crm.model.Customer;

public record CustomerDTO(String firstName, String lastName) {
    public CustomerDTO(Customer customer) {
        this(customer.getFirstName(), customer.getLastName());
    }
}

package de.apollo7321.crm.controller;

import de.apollo7321.crm.dto.CreateCustomerDTO;
import de.apollo7321.crm.dto.CustomerDTO;
import de.apollo7321.crm.service.CustomerService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Page<CustomerDTO> getAll(Pageable pageable) {
        return customerService.getAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@Valid @RequestBody CreateCustomerDTO customer) {
        return customerService.create(customer);
    }
}

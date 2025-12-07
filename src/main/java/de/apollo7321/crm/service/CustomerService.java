package de.apollo7321.crm.service;

import de.apollo7321.crm.dto.CreateCustomerDTO;
import de.apollo7321.crm.dto.CustomerDTO;
import de.apollo7321.crm.model.Customer;
import de.apollo7321.crm.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<CustomerDTO> getAll(Pageable pageable) {
        var customers = customerRepository.findAll(pageable).map(CustomerDTO::new);
        logger.debug("Called CustomerService::getAll, found; {} customers", customers.getTotalElements());
        return customers;
    }

    public CustomerDTO create(CreateCustomerDTO customer) {
        return new CustomerDTO(customerRepository.save(new Customer(customer.firstName(), customer.lastName())));
    }
}

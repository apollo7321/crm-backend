package de.apollo7321.crm.repository;

import de.apollo7321.crm.model.Customer;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    
    Customer save(Customer entity);
}

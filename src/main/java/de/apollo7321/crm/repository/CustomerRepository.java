package de.apollo7321.crm.repository;

import de.apollo7321.crm.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

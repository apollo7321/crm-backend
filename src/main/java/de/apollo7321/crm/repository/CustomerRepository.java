package de.apollo7321.crm.repository;

import de.apollo7321.crm.model.Customer;
import org.springframework.data.repository.ListCrudRepository;

// TODO: use ListPagingAndSortingRepository
public interface CustomerRepository extends ListCrudRepository<Customer, Long> {
}

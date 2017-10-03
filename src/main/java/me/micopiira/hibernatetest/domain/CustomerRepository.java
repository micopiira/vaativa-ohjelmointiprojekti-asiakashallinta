package me.micopiira.hibernatetest.domain;

import me.micopiira.framework.repository.CrudRepository;
import me.micopiira.hibernatetest.jpa.JpaCustomerRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}

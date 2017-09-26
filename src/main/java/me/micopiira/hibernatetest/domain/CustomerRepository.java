package me.micopiira.hibernatetest.domain;


import com.google.inject.ImplementedBy;
import me.micopiira.framework.repository.CrudRepository;
import me.micopiira.hibernatetest.jpa.JpaCustomerRepository;

@ImplementedBy(JpaCustomerRepository.class)
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}

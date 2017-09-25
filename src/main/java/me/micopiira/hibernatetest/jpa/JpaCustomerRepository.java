package me.micopiira.hibernatetest.jpa;

import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;
import me.micopiira.hibernatetest.framework.repository.jpa.JpaRepository;

import javax.persistence.EntityManagerFactory;

public class JpaCustomerRepository extends JpaRepository<Customer, Long> implements CustomerRepository {

	public JpaCustomerRepository(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory, Customer.class);
	}

}

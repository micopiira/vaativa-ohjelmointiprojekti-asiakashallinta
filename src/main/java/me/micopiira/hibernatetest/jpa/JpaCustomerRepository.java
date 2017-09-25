package me.micopiira.hibernatetest.jpa;

import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaCustomerRepository extends JpaRepository<Customer, Long> implements CustomerRepository {

	public JpaCustomerRepository(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory, Customer.class);
	}

}

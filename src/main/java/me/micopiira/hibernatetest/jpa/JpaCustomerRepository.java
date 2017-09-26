package me.micopiira.hibernatetest.jpa;

import com.google.inject.Provides;
import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;
import me.micopiira.framework.repository.jpa.JpaRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;

@Singleton
public class JpaCustomerRepository extends JpaRepository<Customer, Long> implements CustomerRepository {

	@Inject
	public JpaCustomerRepository(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory, Customer.class);
	}

}

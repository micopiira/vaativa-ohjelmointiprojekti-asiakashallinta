package me.micopiira.hibernatetest.jpa;

import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;
import me.micopiira.framework.repository.jpa.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class JpaCustomerRepository extends JpaRepository<Customer, Long> implements CustomerRepository {

	@Autowired
	public JpaCustomerRepository() {
		super(Customer.class);
	}

}

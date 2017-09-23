package me.micopiira.hibernatetest.jpa;

import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaCustomerRepository extends JpaRepository<Customer, Long> implements CustomerRepository {

	@Override
	public List<Customer> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Customer> result = entityManager.createQuery("from Customer ", Customer.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

	@Override
	public void deleteById(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.getReference(Customer.class, id));
		entityManager.getTransaction().commit();
	}

	@Override
	public Customer findOne(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Customer result = entityManager.find(Customer.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

}

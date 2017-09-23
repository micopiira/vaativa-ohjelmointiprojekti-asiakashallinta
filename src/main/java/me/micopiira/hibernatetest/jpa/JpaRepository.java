package me.micopiira.hibernatetest.jpa;

import me.micopiira.hibernatetest.repository.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class JpaRepository<T, ID> implements Repository<T, ID> {

	EntityManagerFactory entityManagerFactory;

	@Override
	public void delete(T entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
		entityManager.getTransaction().commit();
	}

	@Override
	public T save(T entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

}

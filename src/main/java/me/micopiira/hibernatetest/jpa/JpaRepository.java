package me.micopiira.hibernatetest.jpa;

import me.micopiira.hibernatetest.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Function;

public abstract class JpaRepository<T, ID> implements CrudRepository<T, ID> {

	private EntityManagerFactory entityManagerFactory;
	private final Class<T> entityClass;

	JpaRepository(EntityManagerFactory entityManagerFactory, Class<T> entityClass) {
		this.entityManagerFactory = entityManagerFactory;
		this.entityClass = entityClass;
	}

	private <S> S transactional(Function<EntityManager, S> function) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		S result = function.apply(entityManager);
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

	@Override
	public List<T> findAll() {
		return transactional(em -> em.createQuery("from " + entityClass.getName(), entityClass).getResultList());
	}

	@Override
	public void delete(T entity) {
		transactional(em -> {
			em.remove(em.contains(entity) ? entity : em.merge(entity));
			return null;
		});
	}

	@Override
	public T save(T entity) {
		return transactional(em -> {
			em.persist(entity);
			return entity;
		});
	}

	@Override
	public T findOne(ID id) {
		return transactional(em -> em.find(entityClass, id));
	}

	@Override
	public void deleteById(ID id) {
		transactional(em -> {
			em.remove(em.getReference(entityClass, id));
			return null;
		});
	}

}

package me.micopiira.hibernatetest.framework.repository.jpa;

import me.micopiira.hibernatetest.framework.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class JpaRepository<T, ID> implements CrudRepository<T, ID> {

	private final EntityManagerFactory entityManagerFactory;
	private final Class<T> entityClass;

	protected JpaRepository(EntityManagerFactory entityManagerFactory, Class<T> entityClass) {
		this.entityManagerFactory = entityManagerFactory;
		this.entityClass = entityClass;
	}

	private void transactionalVoid(Consumer<EntityManager> consumer) {
		transactional(entityManager -> {
			consumer.accept(entityManager);
			return null;
		});
	}

	private <S> S transactional(Function<EntityManager, S> function) {
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		final S result = function.apply(entityManager);
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
		transactionalVoid(em ->
			em.remove(em.contains(entity) ? entity : em.merge(entity))
		);
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
		transactionalVoid(em -> em.remove(em.getReference(entityClass, id)));
	}

}

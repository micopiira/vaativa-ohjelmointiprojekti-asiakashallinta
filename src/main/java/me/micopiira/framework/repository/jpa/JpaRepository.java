package me.micopiira.framework.repository.jpa;

import me.micopiira.framework.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class JpaRepository<T, ID> implements CrudRepository<T, ID> {

	@PersistenceContext
	protected EntityManager entityManager;
	private final Class<T> entityClass;

	protected JpaRepository(Class<T> entityClass) {
		this.entityClass = entityClass;
	}


	@Override
	public List<T> findAll() {
		return entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList();
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Override
	public T save(T entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public T findOne(ID id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public void deleteById(ID id) {
		entityManager.remove(entityManager.getReference(entityClass, id));
	}

}

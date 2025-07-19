package dogBeautySaloon.dao;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DAO<T extends Object> {
	
	protected EntityManagerFactory emf;
	Class<T> entityClass;
	
	public void create(T entity)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public T read(int id)
	{
		EntityManager em = getEntityManager();
		T entity = null;
		try {
			entity = (T) em.find(entityClass, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return entity;
	}
	
	public void update(T entity)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
	}

	public void delete(int id)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			T entity = (T) em.find(entityClass, id);
			if (entity != null) {
				em.remove(entity);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	protected DAO(String persistenceUnitName, Class<T> entityClass) {
		this.entityClass = entityClass;
		this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
	}
	
	public DAO(String persistenceUnitName, Map<String, String> properties, Class<T> entityClass) {
		this.entityClass = entityClass;
		this.emf = Persistence.createEntityManagerFactory(persistenceUnitName, properties);
	}
	
}

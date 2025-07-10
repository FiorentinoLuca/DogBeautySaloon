package dogBeautySaloon.dao;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DAO<T> {
	
	protected EntityManagerFactory emf;
	
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
	
	@SuppressWarnings("unchecked")
	public T read(int id)
	{
		EntityManager em = getEntityManager();
		T entity = null;
		try {
			entity = (T) em.find(getClass(), id);
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
			@SuppressWarnings("unchecked")
			T entity = (T) em.find(getClass(), id);
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
	
	protected DAO(String persistenceUnitName) {
		this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
	}
	
	public DAO(String persistenceUnitName, Map<String, String> properties) {
		this.emf = Persistence.createEntityManagerFactory(persistenceUnitName, properties);
	}
	
}

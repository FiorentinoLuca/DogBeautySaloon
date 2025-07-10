package dogBeautySaloon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import dogBeautySaloon.model.Owner;
import dogBeautySaloon.model.Pet;

public class OwnerDAO extends DAO<Owner> {
	
	public OwnerDAO() {
		super("DogBeautySaloon");
	}
	
	public Owner findByPet(Pet pet) {
		EntityManager em = getEntityManager();
		Owner owner = null;
		try {
			String jpql = "SELECT o FROM Owner o JOIN o.pets p WHERE p.id = :petId";
			owner = em.createQuery(jpql, Owner.class)
					.setParameter("petId", pet.getId())
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return owner;
	}
	
	List<Owner> findAll() {
		EntityManager em = getEntityManager();
		List<Owner> owners = null;
		try {
			owners = em.createQuery("SELECT o FROM Owner o", Owner.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return owners;
	}
	
}

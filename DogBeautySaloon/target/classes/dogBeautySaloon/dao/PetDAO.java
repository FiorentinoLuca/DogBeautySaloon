package dogBeautySaloon.dao;

import javax.persistence.EntityManager;

import java.util.List;
import dogBeautySaloon.model.Pet;

public class PetDAO extends DAO<Pet> {

	public PetDAO() {
		super("DogBeautySaloon", Pet.class);
	}
	
	public List<Pet> readAll() {
		EntityManager em = null;
		List<Pet> pets = null;
		try {
			em = getEntityManager();
			pets = em.createQuery("SELECT p FROM Pet p", Pet.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return pets;
	}
	
}

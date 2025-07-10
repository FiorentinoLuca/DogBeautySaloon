package dogBeautySaloon.model;

import javax.persistence.*;

@Entity
@Table(schema = "\"dogBeautySaloon\"", name = "\"Pet\"")
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String breed;
	private String color;
	private String allergeens;
	private String specialTreatment;
	private String observations;
	
	@OneToOne
	private Owner owner;

	public Pet() {
		super();
	}

	public Pet(int id, String name, String breed, String color, String allergeens, String specialTreatment,
			String observations, Owner owner) {
		super();
		this.id = id;
		this.name = name;
		this.breed = breed;
		this.color = color;
		this.allergeens = allergeens;
		this.specialTreatment = specialTreatment;
		this.observations = observations;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBreed() {
		return breed;
	}

	public String getColor() {
		return color;
	}

	public String getAllergeens() {
		return allergeens;
	}

	public String getSpecialTreatment() {
		return specialTreatment;
	}

	public String getObservations() {
		return observations;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setAllergeens(String allergeens) {
		this.allergeens = allergeens;
	}

	public void setSpecialTreatment(String specialTreatment) {
		this.specialTreatment = specialTreatment;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	
	
}

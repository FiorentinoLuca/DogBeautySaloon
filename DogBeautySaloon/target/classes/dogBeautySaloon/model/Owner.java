package dogBeautySaloon.model;

import javax.persistence.*;

@Entity
@Table(schema = "\"dogBeautySaloon\"", name = "\"Owner\"")
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	private String cellphone;
	
	public Owner() {
	}

	public Owner(int id, String name, String cellphone) {
		super();
		this.id = id;
		this.name = name;
		this.cellphone = cellphone;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCellphone() {
		return cellphone;
	}
	
}

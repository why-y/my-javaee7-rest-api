package ch.gry.myjavaee7project1.musicshelf.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Dummy extends Model {
	
	private static final long serialVersionUID = 1L;

	@Column
	String name;
	
	@Column
	Long value;

	public Dummy() {
	}
	
	public Dummy(String name, Long value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}

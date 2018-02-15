package mz.ciuem.sipma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="rubric")
public class Rubric extends IdEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "name")
	private String name;
	
	@Column(name = "designation")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "super_rubric")
	private SuperRubric superRubric;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SuperRubric getSuperRubric() {
		return superRubric;
	}

	public void setSuperRubric(SuperRubric superRubric) {
		this.superRubric = superRubric;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "super_rubric")
@Access(AccessType.FIELD)
public class SuperRubric extends IdEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria")
	private Categoria categoria;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "superRubric")
	private List<Rubric> rubrics= new ArrayList<Rubric>();

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Rubric> getRubrics() {
		return rubrics;
	}

	public void setRubrics(List<Rubric> rubrics) {
		this.rubrics = rubrics;
	}

   

}

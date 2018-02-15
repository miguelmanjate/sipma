package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sub_rubric")
public class SubRubric extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;

	@Column(name = "designation")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rubric")
	private Rubric rubric;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "subRubric")
	private List<BasedCalculating> basedCalculatings = new ArrayList<BasedCalculating>();

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

	public Rubric getRubric() {
		return rubric;
	}

	public void setRubric(Rubric rubric) {
		this.rubric = rubric;
	}

	public List<BasedCalculating> getBasedCalculatings() {
		return basedCalculatings;
	}

	public void setBasedCalculatings(List<BasedCalculating> basedCalculatings) {
		this.basedCalculatings = basedCalculatings;
	}
	
	
}

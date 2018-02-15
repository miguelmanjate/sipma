package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plan")
public class Plan extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "plan")
	private List<Action> actions = new ArrayList<Action>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organ")
	private Organ organ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

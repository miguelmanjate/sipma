package mz.ciuem.sipma.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project_rubric")
public class ProjectRubric extends IdEntity {

	private static final long serialVersionUID = 1L;

	private boolean emUso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_rubric", nullable = true)
	private SubRubric subRubric;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_investiment")
	private ProjectInvestment projectInvestment;

	public SubRubric getSubRubric() {
		return subRubric;
	}

	public void setSubRubric(SubRubric subRubric) {
		this.subRubric = subRubric;
	}

	public ProjectInvestment getProjectInvestment() {
		return projectInvestment;
	}

	public void setProjectInvestment(ProjectInvestment projectInvestment) {
		this.projectInvestment = projectInvestment;
	}

	public boolean isEmUso() {
		return emUso;
	}

	public void setEmUso(boolean emUso) {
		this.emUso = emUso;
	}

}

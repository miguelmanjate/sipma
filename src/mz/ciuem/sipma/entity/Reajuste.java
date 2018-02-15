package mz.ciuem.sipma.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="reajuste")
public class Reajuste extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "distDistribuicaoUnidade_id")
	private DistDistribuicaoUnidade distDistribuicaoUnidade;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accaoEspecifica_id")
	private SpecifiedAction accaoEspecifica;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reajuste")
	private List<PlanoTesouraria> planoTesouraria;
	
}

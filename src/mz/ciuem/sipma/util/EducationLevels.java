package mz.ciuem.sipma.util;

public enum EducationLevels {

//	Alfabetizacao("Alfabetiza√ß√£o"), 
//	EP1("Ensino Prim√°rio 1o Grau"),
//	EP2("Ensino Prim√°rio 2o Grau"),
//  ESG1("Ensino Secund·rio Geral 1o Ciclo"),
//	ESG2("Ensino Secund·rio Geral 2o Ciclo"),
//	ETE("Ensino TÈcnico Elementar"),
	ETB("Ensino  B·sico"),
	ETM("Ensino  MÈdio"),
//	CFP("Curso de FormaÁ„o de Professores"),
	ETP("Ensino TÈcnico-Profissional"),
	Licensiado("Licenciatura"),
	Mestre("Mestrado"),
	Doutor("Doutoramento");

	final String value;

	EducationLevels(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}

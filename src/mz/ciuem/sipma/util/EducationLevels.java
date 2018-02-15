package mz.ciuem.sipma.util;

public enum EducationLevels {

//	Alfabetizacao("Alfabetização"), 
//	EP1("Ensino Primário 1o Grau"),
//	EP2("Ensino Primário 2o Grau"),
//  ESG1("Ensino Secund�rio Geral 1o Ciclo"),
//	ESG2("Ensino Secund�rio Geral 2o Ciclo"),
//	ETE("Ensino T�cnico Elementar"),
	ETB("Ensino  B�sico"),
	ETM("Ensino  M�dio"),
//	CFP("Curso de Forma��o de Professores"),
	ETP("Ensino T�cnico-Profissional"),
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

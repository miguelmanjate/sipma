package mz.ciuem.sipma.util;

public enum EducationLevels {

//	Alfabetizacao("Alfabetização"), 
//	EP1("Ensino Primário 1o Grau"),
//	EP2("Ensino Primário 2o Grau"),
	ESG1("Ensino Secund�rio Geral 1o Ciclo"),
	ESG2("Ensino Secund�rio Geral 2o Ciclo"),
	ETE("Ensino T�cnico Elementar"),
	ETB("Ensino T�cnico B�sico"),
	ETM("Ensino T�cnico M�dio"),
	CFP("Curso de Forma��o de Professores"),
	Licensiado("Licensiado"),
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

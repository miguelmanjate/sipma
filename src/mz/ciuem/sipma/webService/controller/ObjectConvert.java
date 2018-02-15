package mz.ciuem.sipma.webService.controller;

import java.util.ArrayList;
import java.util.List;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.webService.model.EmployeWebService;

public class ObjectConvert {

	
	
	public List<EmployeWebService> ListOfEmploye(List<Employee> lista) {
		List<EmployeWebService> listaE=new ArrayList<EmployeWebService>();
		for (Employee employee : lista) {
			EmployeWebService object=new EmployeWebService();
			object.setAcademicLevel(employee.getAcademicLevel());
			object.setAddress(employee.getAddress());
			object.setGender(employee.getGender());
			object.setName(employee.getName());
			object.setLastName(employee.getLastName());
			object.setNationality(employee.getNationality());
			object.setMaritalStatus(employee.getMaritalStatus());
			listaE.add(object);
		}

		return listaE;
	}

	
	public EmployeWebService ObjectOfemploye(Employee employee){
		EmployeWebService object=new EmployeWebService();
		object.setAcademicLevel(employee.getAcademicLevel());
		object.setAddress(employee.getAddress());
		object.setGender(employee.getGender());
		object.setName(employee.getName());
		object.setLastName(employee.getLastName());
		object.setNationality(employee.getNationality());
		object.setMaritalStatus(employee.getMaritalStatus());
		
		return object;
	}
}

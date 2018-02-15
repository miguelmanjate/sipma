package mz.ciuem.sipma.webService.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Spring;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import com.google.gson.Gson;

import mz.ciuem.sipma.dao.EmployeeDao;
import mz.ciuem.sipma.dao.impl.EmployeeDaoImpl;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.vm.AbstractVM;
import mz.ciuem.sipma.webService.model.EmployeWebService;

public class EmployesController extends AbstractVM{

	 @WireVariable
	 private EmployeeService _empService;
	
	List<Employee> listEmploye;

	EmployeeDaoImpl o=new EmployeeDaoImpl();
	ObjectConvert object = new ObjectConvert();
	private Gson gson = new Gson();

	public static void main(String[] args) {
		EmployesController e = new EmployesController();
		System.out.println(e.getAlll());
	}
	
	public void init(){
		listEmploye = _empService.getAll();
	}

	
	public String getAlll() {
		init();
		List<EmployeWebService> lista = new ArrayList<EmployeWebService>();
		if(listEmploye!=null)lista = object.ListOfEmploye(listEmploye);
//		String json = gson.toJson(lista);
		Messagebox.show("");
		
		return "sds";
	}
}

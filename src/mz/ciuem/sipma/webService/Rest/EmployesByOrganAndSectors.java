package mz.ciuem.sipma.webService.Rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.select.annotation.WireVariable;

import mz.ciuem.sipma.webService.controller.EmployesController;
import mz.ciuem.sipma.webService.model.EmployeWebService;

@WebServlet(urlPatterns = "/emp")
public class EmployesByOrganAndSectors extends HttpServlet{

	
	EmployesController employ=new EmployesController();
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {

		PrintWriter outPut = resp.getWriter();
		outPut.print("[{nome:gabriel Fernando,email:gab@gmail}]");
    }
}

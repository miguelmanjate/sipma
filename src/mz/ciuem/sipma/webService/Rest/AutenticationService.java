package mz.ciuem.sipma.webService.Rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.entity.User_Session;
import mz.ciuem.sipma.service.UserService;
import mz.ciuem.sipma.vm.MainVM;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/autenticationLoggerUser", loadOnStartup = 1)
public class AutenticationService extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		User_Session user_session = new User_Session();
		Gson gson = new Gson();
		String user = req.getParameter("param1");
		user_session.setId_orgao(MainVM.id_organ);
		user_session.setUser_name(MainVM.user_session);

		String json = gson.toJson(user_session);
		out.print(json);

	}

}

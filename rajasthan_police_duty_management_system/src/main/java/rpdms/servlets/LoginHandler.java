package rpdms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rpdms.dao.DaoImpl;

@WebServlet("/login")
public class LoginHandler extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String result = null;
		
		String role = (String) req.getParameter("role");

		switch (role) {
		case "admin": {
			adminLogin(req, resp);
			break;
		}
		case "officer": {
			officerLogin(req, resp);
			break;
		}

		}
	}

	private void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String employeeId=req.getParameter("employeeId");
		String password= req.getParameter("password");

		boolean isValid = new DaoImpl().isRegisteredAdmin(employeeId,password);
		
		if(isValid) {
			HttpSession session=req.getSession();
			session.removeAttribute("adminId");
			session.setAttribute("adminId",employeeId);
			
			
			RequestDispatcher rd=req.getRequestDispatcher("admin-home");
			try {
				rd.forward(req, resp);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			RequestDispatcher rd=req.getRequestDispatcher(".");
			try {
				rd.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void officerLogin(HttpServletRequest req, HttpServletResponse resp) {
		String employeeId=req.getParameter("employeeId");
		String password= req.getParameter("password");

		boolean isValid = new DaoImpl().isRegisteredOfficer(employeeId,password);
		
		if(isValid) {
			HttpSession session=req.getSession();
			session.setAttribute("employeeId",employeeId);
			RequestDispatcher rd=req.getRequestDispatcher("officer-home");
			try {
				rd.forward(req, resp);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			RequestDispatcher rd=req.getRequestDispatcher(".");
			try {
				rd.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}

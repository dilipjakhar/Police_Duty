package rpdms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rpdms.dao.DaoImpl;
import rpdms.dto.Duty;

@WebServlet("/new-duty")
public class RegisterNewDutyPointServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		Duty duty=new Duty();
		String dutyPoint=req.getParameter("dutyPoint");
		String dutyDesc=req.getParameter("dutyDesc");
		duty.setDutyPoint(dutyPoint);
		duty.setDutyDesc(dutyDesc);
		boolean isInsert=new DaoImpl().saveNewDutyPoint(duty);
		

		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		out.println("<script>");
		out.println("alert('New Duty point added');");
		out.println("</script>");
		
		RequestDispatcher rd=req.getRequestDispatcher("admin-home");
		rd.forward(req, resp);
//		resp.sendRedirect("admin-home");
				
	}

}

package rpdms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.Session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rpdms.dao.DaoImpl;

@WebServlet("/officer-home")
public class OfficerHomeServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String employeeId=(String) session.getAttribute("employeeId");
		if(employeeId!=null) {
			DaoImpl dao=new DaoImpl();
			ResultSet rsAssingDuty=dao.getAssignDuty(employeeId);
					
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			
			// Generate HTML table dynamically
	        out.println("<html><head><title>Records</title></head><body>");
	        out.println("<h2>Records</h2>");
	        out.println("<table border='1'><tr><th>Belt No</th><th>Name</th><th>Start Duty</th><th>End Duty</th><th>Duty Point</th></tr>");
	        
	        // Iterate over records and add rows to the table
	        try {
				while (rsAssingDuty.next()) {
				    out.println("<tr>");
				    
				    
				    	out.println("<td>" + rsAssingDuty.getInt(1) + "</td>");
				        out.println("<td>" + rsAssingDuty.getString(2) + "</td>");
				        out.println("<td>" + rsAssingDuty.getString(3) + "</td>");
				        out.println("<td>" + rsAssingDuty.getString(4) + "</td>");
						out.println("<td>" + rsAssingDuty.getString(5) + "</td>");
					
				    out.println("</tr>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        out.println("</table>");
	        out.println("</body></html>");
		}else {
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			out.print("sorry");
		}
		
		
	}

}

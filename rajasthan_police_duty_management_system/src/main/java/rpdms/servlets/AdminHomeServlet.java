package rpdms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import com.mysql.cj.Session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rpdms.dao.DaoImpl;
import rpdms.dto.Duty;

@WebServlet("/admin-home")
public class AdminHomeServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
				
		String adminId=(String) session.getAttribute("adminId");
			
		if(adminId!=null) {
						
			DaoImpl dao=new DaoImpl();
			ResultSet rsEmp=dao.getEmp(adminId);
			ResultSet rsDuty=dao.getDuty(adminId);
			ResultSet rsAssingDuty=dao.getAllAssignDuty();
			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
						
			// Generate HTML dynamically
			out.println("<html><head><title>Records</title>");
			out.println("<style>");
			out.println("table {");
			out.println("    width: 100%;");
			out.println("    border-collapse: collapse;");
			out.println("}");
			out.println("table, th, td {");
			out.println("    border: 1px solid black;");
			out.println("}");
			out.println("th, td {");
			out.println("    padding: 8px;");
			out.println("    text-align: left;");
			out.println("}");
			out.println("th {");
			out.println("    background-color: #f2f2f2;");
			out.println("}");
			out.println(".container {");
			out.println("    display: flex;");
			out.println("}");
			out.println(".left-half {");
			out.println("    width: 50%;");
			out.println("    float: left;");
			out.println("}");
			out.println(".right-half {");
			out.println("    width: 50%;");
			out.println("    float: right;");
			out.println("}");
			out.println("</style>");
			out.println("</head><body>");

			// Input form
			out.println("<div class='container'>");
			out.println("<div class='left-half'>");
			out.println("<form action='duty'>");
			out.println("<label for='label1'>Officer Name:</label><br>");
			out.println("<select id='officerName' name='employeeId'>");
			try {
				while (rsEmp.next()) {
					String name=rsEmp.getString("emp_name");
					String empId=rsEmp.getString("employee_id");
					int beltNo=rsEmp.getInt("belt_no");
				    out.println("<option value='" + empId + "'>" + name+"("+beltNo+")" + "</option>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</select><br>");
			out.println("<label for='label3'>Start Time:</label><br>");
			out.println("<input type='datetime-local' id='label3' name='startTime'><br>");
			
			
			out.println("</div>");
			out.println("<div class='right-half'>");
			
			out.println("<label for='label2'>Duty Point:</label><br>");
			out.println("<select id='dutyPoint' name='duty_id' onchange='showForm(this)'>");
			try {
				while (rsDuty.next()) {
					String duty=rsDuty.getString("duty_point");
					int dutyId=rsDuty.getInt("duty_id");
				    out.println("<option value='" + dutyId + "'>" + duty + "</option>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<option value='--new Entry--'>--new Entry--</option>");
			out.println("</select><br>");
			out.println("<div id='additionalFields' style='display: none;'>");
			out.println("<label for='additionalField1'>Duty Point:</label><br>");
			out.println("<input type='text' id='additionalField1' name='dutyPoint'><br>");
			out.println("<label for='additionalField2'>Duty Descreption:</label><br>");
			out.println("<input type='text' id='additionalField2' name='dutyDesc'><br>");
			out.println("<input type='button' value='Add' onclick='submitForm()'>");
			out.println("</div>");

			out.println("<script>");
			out.println("function showForm(select) {");
			out.println("  var additionalFields = document.getElementById('additionalFields');");
			out.println("  if (select.value == '--new Entry--') {");
			out.println("    additionalFields.style.display = 'block';");
			out.println("  } else {");
			out.println("    additionalFields.style.display = 'none';");
			out.println("  }");
			out.println("}");

			out.println("function submitForm() {");
			out.println("  var dutyPoint = document.getElementById('additionalField1').value;");
			out.println("  var dutyDesc = document.getElementById('additionalField2').value;");
			out.println("  var officerName = document.getElementById('officerName').value;");
			out.println("  var startTime = document.getElementById('label3').value;");
			out.println("  var endTime = document.getElementById('label4').value;");
			out.println("  var form = document.createElement('form');");
			out.println("  form.method = 'post';");
			out.println("  form.action = 'admin-home';");
			out.println("  var params = { officerName: officerName, startTime: startTime, endTime: endTime, dutyPoint: dutyPoint, dutyDesc: dutyDesc };");
			out.println("  for (var key in params) {");
			out.println("    var input = document.createElement('input');");
			out.println("    input.type = 'hidden';");
			out.println("    input.name = key;");
			out.println("    input.value = params[key];");
			out.println("    form.appendChild(input);");
			out.println("  }");
			out.println("  document.body.appendChild(form);");
			out.println("  form.submit();");
			out.println("}");
			out.println("</script>");


			
			out.println("<label for='label4'>End Time:</label><br>");
			out.println("<input type='datetime-local' id='label4' name='endTime'><br>");
			out.println("<input type='submit' value='submit'><br>");
			out.println("</form>");
			
			out.println("<script>");
	        out.println("var now = new Date();");
	        out.println("var year = now.getFullYear();");
	        out.println("var month = ('0' + (now.getMonth() + 1)).slice(-2);");
	        out.println("var day = ('0' + now.getDate()).slice(-2);");
	        out.println("var hours = ('0' + now.getHours()).slice(-2);");
	        out.println("var minutes = ('0' + now.getMinutes()).slice(-2);");
	        out.println("var datetime = year + '-' + month + '-' + day + 'T' + hours + ':' + minutes;");
	        out.println("document.getElementById('label3').value = datetime;");
	        out.println("document.getElementById('label4').value = datetime;");
	        out.println("</script>");
			
			out.println("</div>");
			out.println("</div>");

			// Table
			out.println("<table>");
			out.println("<tr><th>Belt No</th><th>Name</th><th>Start Duty</th><th>End Duty</th><th>Duty Point</th></tr>");
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
			    e.printStackTrace();
			}
			out.println("</table>");

			out.println("</body></html>");

		}else {
			
		}
	}

//	@Override
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Duty duty=new Duty();
		String dutyPoint=req.getParameter("dutyPoint");
		String dutyDesc=req.getParameter("dutyDesc");
		duty.setDutyPoint(dutyPoint);
		duty.setDutyDesc(dutyDesc);
		boolean isInsert=new DaoImpl().saveNewDutyPoint(duty);
		
		doGet(req, resp);
		
	}

}

package rpdms.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rpdms.dao.DaoImpl;
import rpdms.dto.Duty;
import rpdms.dto.DutyAssign;

@WebServlet("/duty")
public class DutyManagementSystemServlet extends HttpServlet {

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assign a new duty
    	
        try {
            String employeeId = request.getParameter("employeeId");
            int ps_cd = 1; // Assuming admin ID is always 1 (you can modify this as needed)
            String duty_id=request.getParameter("duty_id");
            System.out.println(request.getParameter("startTime"));            
            Date startTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("startTime"));
            Date endTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("endTime"));
            
            DutyAssign newDuty = new DutyAssign();
            	newDuty.setEmp_id(employeeId);
            	newDuty.setPs_cd(ps_cd);
            	newDuty.setDuty_id(Integer.parseInt(duty_id));
            	newDuty.setStart_time(startTime);
            	
            	newDuty.setEnd_time(endTime);


            new DaoImpl().saveDutyToDatabase(newDuty);

//             Redirect to the duty list page
//            response.sendRedirect("admin-home");
            RequestDispatcher rd=request.getRequestDispatcher("admin-home");
            rd.forward(request, response);
            
        } catch (ParseException e) {
            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
        }
    }

}

package rpdms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rpdms.encryption_dcryption.EncryptorDcryptor;

import rpdms.util.ConnectionFactory;

import rpdms.dto.Duty;
import rpdms.dto.DutyAssign;


public class DaoImpl implements Dao{
	public void saveDutyToDatabase(DutyAssign newDuty) {
		Connection con=ConnectionFactory.getConnection();
		String query="INSERT INTO duty_assignments (employee_id, ps_cd, duty_start_time, duty_end_time,duty_id) VALUES (?, ?, ?, ?,?)";
		
        try{
        	PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newDuty.getEmp_id());
            statement.setInt(2, newDuty.getPs_cd());
            statement.setTimestamp(3, new java.sql.Timestamp(newDuty.getStart_time().getTime()));
            statement.setTimestamp(4, new java.sql.Timestamp(newDuty.getEnd_time().getTime()));
            statement.setInt(5,newDuty.getDuty_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public boolean saveNewDutyPoint(Duty duty) {
		String query="INSERT INTO duty (duty_point,duty_desc) VALUES (?, ?)";
		Connection con=ConnectionFactory.getConnection();
        try{
        	PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, duty.getDutyPoint());
            statement.setString(2, duty.getDutyDesc());
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
		
	}

	public ResultSet getEmp(String gdc_id) {
		String query="select * from employees e left join duty_assignments a using (employee_id)  where e.ps_cd=(select e.ps_cd from employees e where e.employee_id =? and role='admin') order by duty_assignment_id asc;";
		Connection con=ConnectionFactory.getConnection();
        try{
        	PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, gdc_id);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	public ResultSet getDuty(String gdc_id) {
		String query="select * from duty";
		Connection con=ConnectionFactory.getConnection();
		System.out.println(con);
        try {
        	Statement statement = con.createStatement();
            
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	public boolean isRegisteredAdmin(String employeeId, String password) {
		String query="select password from employees where employee_id =? and role='admin'";
		Connection con=ConnectionFactory.getConnection();
        try{
        	PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, employeeId);
            ResultSet rs=statement.executeQuery();
   			rs.next();
			return rs.getString("password").equals(password);
//			return (EncryptorDcryptor.verifyPassword(password,rs.getString("Password")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	public boolean isRegisteredOfficer(String employeeId, String password) {
		String query="select password from employees where employee_id =? and role='officer'";
		Connection con=ConnectionFactory.getConnection();
        try {
        	PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, employeeId);
            ResultSet rs=statement.executeQuery();
			
			rs.next();
			return rs.getString("password").equals(password);
//			return (EncryptorDcryptor.verifyPassword(password,rs.getString("Password")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	public ResultSet getAssignDuty(String employeeId) {
		String query="select e.belt_no,e.emp_name,d.duty_start_time,d.duty_end_time, dd.duty_point from duty_assignments d"
				+ " join duty dd on d.duty_id =dd.duty_id join employees e \r\n"
				+ "    on e.employee_id=d.employee_id  where e.employee_id =?";
		Connection con=ConnectionFactory.getConnection();
        try {
        	PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, employeeId);
            return statement.executeQuery();
					
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	public ResultSet getAllAssignDuty() {
		String query="select e.belt_no,e.emp_name,d.duty_start_time,d.duty_end_time, dd.duty_point from duty_assignments d join duty dd on d.duty_id =dd.duty_id join employees e on e.employee_id=d.employee_id";
		Connection con=ConnectionFactory.getConnection();
        try {
        	Statement statement = con.createStatement();
            return statement.executeQuery(query);
					
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

}

package rpdms.dao;

import java.sql.ResultSet;

import rpdms.dto.Duty;
import rpdms.dto.DutyAssign;

public interface Dao {
	void saveDutyToDatabase(DutyAssign newDuty);
	boolean saveNewDutyPoint(Duty duty);
	ResultSet getEmp(String gdc_id);
	ResultSet getDuty(String gdc_id);
	boolean isRegisteredAdmin(String employeeId, String password);
	boolean isRegisteredOfficer(String employeeId, String password);
	ResultSet getAssignDuty(String employeeId);
	ResultSet getAllAssignDuty();
}

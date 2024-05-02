package rpdms.dto;

import java.util.Date;

public class DutyAssign {
	
	private String emp_id;
	private Date duty_date;
	private Date start_time;
	private Date end_time;
	private int duty_id;
	private int ps_cd;
	
	
	public int getPs_cd() {
		return ps_cd;
	}
	public void setPs_cd(int ps_cd) {
		this.ps_cd = ps_cd;
	}
	public int getDuty_id() {
		return duty_id;
	}
	public void setDuty_id(int duty_id) {
		this.duty_id = duty_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public Date getDuty_date() {
		return duty_date;
	}
	public void setDuty_date(Date duty_date) {
		this.duty_date = duty_date;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
}

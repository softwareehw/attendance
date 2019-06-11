package bean;

import java.util.Date;

public class WorkArrangement {
	//申请人
	private int employeeId;
	//安排人
	private int arrangePerson;
	//工作安排id
	private int work_arrange_id;
	
	private Date morningStartTime;
	private Date morningEndTime;
	
	private Date afternoonStartTime;
	private Date afternoonEndTime;
	
	private Date eveningStartTime;
	private Date eveningEndTime;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getArrangePerson() {
		return arrangePerson;
	}
	public void setArrangePerson(int arrangePerson) {
		this.arrangePerson = arrangePerson;
	}
	public int getWork_arrange_id() {
		return work_arrange_id;
	}
	public void setWork_arrange_id(int work_arrange_id) {
		this.work_arrange_id = work_arrange_id;
	}
	public Date getMorningStartTime() {
		return morningStartTime;
	}
	public void setMorningStartTime(Date morningStartTime) {
		this.morningStartTime = morningStartTime;
	}
	public Date getMorningEndTime() {
		return morningEndTime;
	}
	public void setMorningEndTime(Date morningEndTime) {
		this.morningEndTime = morningEndTime;
	}
	public Date getAfternoonStartTime() {
		return afternoonStartTime;
	}
	public void setAfternoonStartTime(Date afternoonStartTime) {
		this.afternoonStartTime = afternoonStartTime;
	}
	public Date getAfternoonEndTime() {
		return afternoonEndTime;
	}
	public void setAfternoonEndTime(Date afternoonEndTime) {
		this.afternoonEndTime = afternoonEndTime;
	}
	public Date getEveningStartTime() {
		return eveningStartTime;
	}
	public void setEveningStartTime(Date eveningStartTime) {
		this.eveningStartTime = eveningStartTime;
	}
	public Date getEveningEndTime() {
		return eveningEndTime;
	}
	public void setEveningEndTime(Date eveningEndTime) {
		this.eveningEndTime = eveningEndTime;
	}
	
	
	
}

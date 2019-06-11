package bean;

import java.util.Date;

public class WorkArrangement {
	//申请人
	private int employeeId;
	//安排人
	private int arrangePerson;
	//工作安排id
	private int work_arrange_id;
	
	private Date startTime;
	private Date endTime;
	
	
	
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}

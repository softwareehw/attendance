package bean;

import java.util.Date;

public class AttendanceRecord {
	private Date startTime;
	private Date endTime;
	private int employeeId; //主键
	private int isComplete; 
	
	public int getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
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

	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	
}

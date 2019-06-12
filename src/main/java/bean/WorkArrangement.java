package bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WorkArrangement {
	//申请人
	private int employeeId;
	//安排人
	private int arrangePerson;
	//工作安排id
	private int workArrangeId;
	
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
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

	public int getWorkArrangeId() {
		return workArrangeId;
	}
	public void setWorkArrangeId(int workArrangeId) {
		this.workArrangeId = workArrangeId;
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

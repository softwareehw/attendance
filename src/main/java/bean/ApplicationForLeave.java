package bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
//请假
public class ApplicationForLeave {
	
	//是否批准
	private boolean state ;
	//开始时间
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime; 
	//结束时间
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	//申请人id
	private int applicatdPerson;
	//批准人id
	private int ratifiedPerson;
	
	private int leaveId;
	
	private boolean isReportBack;
	
	private Date reportBackTime;
	
	private String leaveReason;
	
	private String rejectReason;

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
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

	public int getApplicatdPerson() {
		return applicatdPerson;
	}

	public void setApplicatdPerson(int applicatdPerson) {
		this.applicatdPerson = applicatdPerson;
	}

	public int getRatifiedPerson() {
		return ratifiedPerson;
	}

	public void setRatifiedPerson(int ratifiedPerson) {
		this.ratifiedPerson = ratifiedPerson;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public boolean isReportBack() {
		return isReportBack;
	}

	public void setReportBack(boolean isReportBack) {
		this.isReportBack = isReportBack;
	}

	public Date getReportBackTime() {
		return reportBackTime;
	}

	public void setReportBackTime(Date reportBackTime) {
		this.reportBackTime = reportBackTime;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	
	
	
}

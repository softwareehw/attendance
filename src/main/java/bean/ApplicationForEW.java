package bean;

import java.util.Date;
//加班申请
public class ApplicationForEW {
	
	private int applicatedId;
	
	private int ewState;
	private int ewId;
	private int ratifyId;
	
	private Date startTime;
	private Date endTime;
	private String ewReason;

	public String getEwReason() {
		return ewReason;
	}
	public void setEwReason(String ewReason) {
		this.ewReason = ewReason;
	}
	public int getApplicatedId() {
		return applicatedId;
	}
	public void setApplicatedId(int applicatedId) {
		this.applicatedId = applicatedId;
	}
	public int getEwState() {
		return ewState;
	}
	public void setEwState(int ewState) {
		this.ewState = ewState;
	}
	public int getEwId() {
		return ewId;
	}
	public void setEwId(int ewId) {
		this.ewId = ewId;
	}
	public int getRatifyId() {
		return ratifyId;
	}
	public void setRatifyId(int ratifyId) {
		this.ratifyId = ratifyId;
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

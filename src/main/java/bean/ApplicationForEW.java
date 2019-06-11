package bean;

import java.util.Date;
//加班申请
public class ApplicationForEW {
	
	private int applicatedId;
	
	private int ewState;
	private int ewId;
	private int ratifyId;
	
	private Date morningStartTime;
	private Date morningEndTime;
	
	private Date afternoonStartTime;
	private Date afternoonEndTime;
	
	private Date eveningStartTime;
	private Date eveningEndTime;
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

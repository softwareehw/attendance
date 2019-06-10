package bean;

import java.util.Date;
//加班申请
public class ApplicationForEW {
	
	private int applicatedId; //申请单号
	private int applicatedPerson;
	private Date date;
	private int state; //0待审，1为批准，2为驳回
	private int sectorId;
	public int getApplicatedId() {
		return applicatedId;
	}
	public void setApplicatedId(int i) {
		this.applicatedId = i;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSectorId() {
		return sectorId;
	}
	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getApplicatedPerson() {
		return applicatedPerson;
	}
	public void setApplicatedPerson(int applicatedPerson) {
		this.applicatedPerson = applicatedPerson;
	}
	
	
}

package bean;

public class Sector {
	private int sectorId;
	//部门名称
	private String sectorName;
	//部门人数
	private int sectorPeopleNumber;
	//部门信息
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSectorId() {
		return sectorId;
	}
	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public int getSectorPeopleNumber() {
		return sectorPeopleNumber;
	}
	public void setSectorPeopleNumber(int sectorPeopleNumber) {
		this.sectorPeopleNumber = sectorPeopleNumber;
	}
}

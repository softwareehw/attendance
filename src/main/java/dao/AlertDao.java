package dao;

import java.util.List;

import bean.Alert;

public interface AlertDao {

	public int addAlert(int employeeId,int state);
	
	public int deleteAlert(int employeeId);
	
	public List<Alert> findAlert(int employeeId);
	
	public int addAlertEmployee(int employeeId,int state);
}

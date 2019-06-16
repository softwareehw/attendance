package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Alert;



public class AlertRowMapper implements  RowMapper<Alert> {

	@Override
	public Alert mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int employeeId = rs.getInt("employee_id");
		int state = rs.getInt("state");
	
		int alertId = rs.getInt("alert_id");
		
		Alert alert = new Alert();
		alert.setEmployeeId(employeeId);
		alert.setAlert_id(alertId);
		
		alert.setState(state);
		
		
		
		return alert;
	}

}

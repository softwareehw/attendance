package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import bean.ApplicationForEW;
import bean.AttendanceRecord;

/**
 *@author 29226
 *@注释 : 实现RowMapper接口，返回applicationForEW对象
*/
public class AttendanceRecordRowMapper implements RowMapper<AttendanceRecord> {

	@Override
	public AttendanceRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		Date startTime = rs.getTimestamp("start_time");
		Date endTime = rs.getTimestamp("end_time");
		int employeeId = rs.getInt("employeeid");
		int isComplete = rs.getInt("is_complete");
		
		AttendanceRecord attendance = new AttendanceRecord();
		
		attendance.setStartTime(startTime);
		attendance.setEndTime(endTime);
		attendance.setEmployeeId(employeeId);
		attendance.setIsComplete(isComplete);
		
		return attendance;
	}

}
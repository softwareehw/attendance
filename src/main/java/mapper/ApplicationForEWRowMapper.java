package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import bean.ApplicationForEW;

/**
 *@author 29226
 *@注释 : 实现RowMapper接口，返回applicationForEW对象
*/
public class ApplicationForEWRowMapper implements RowMapper<ApplicationForEW> {

	@Override
	public ApplicationForEW mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int applicatedId = rs.getInt("applicated_id");
		Date morningStartTime = rs.getDate("morning_start_time");
		Date morningEndTime = rs.getDate("morning_end_time");
		Date afternoonStartTime = rs.getDate("afternoon_start_time");
		Date afternoonEndTime = rs.getDate("afternoon_end_time");
		Date eveningStartTime = rs.getDate("evening_start_time");
		Date eveningEndTime = rs.getDate("evening_end_time");
		int ewState = rs.getInt("ew_state");
		int ewId = rs.getInt("ew_id");
		int ratifyId = rs.getInt("ratify_id");
		
		
		ApplicationForEW applicationForEW = new ApplicationForEW();
		applicationForEW.setApplicatedId(applicatedId);
		applicationForEW.setMorningStartTime(morningStartTime);
		applicationForEW.setMorningEndTime(morningEndTime);
		applicationForEW.setAfternoonStartTime(afternoonStartTime);
		applicationForEW.setAfternoonEndTime(afternoonEndTime);
		applicationForEW.setEveningStartTime(eveningStartTime);
		applicationForEW.setEveningEndTime(eveningEndTime);
		applicationForEW.setEwId(ewId);
		applicationForEW.setEwState(ewState);
		applicationForEW.setRatifyId(ratifyId);
		
		
		
		
		
		return applicationForEW;
	}

}

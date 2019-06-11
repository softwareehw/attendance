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
		Date startTime = rs.getDate("start_time");
		Date endTime = rs.getDate("end_time");
	
		int ewState = rs.getInt("ew_state");
		int ewId = rs.getInt("ew_id");
		int ratifyId = rs.getInt("ratify_id");
		
		
		ApplicationForEW applicationForEW = new ApplicationForEW();
		applicationForEW.setApplicatedId(applicatedId);
		applicationForEW.setStartTime(startTime);
		applicationForEW.setEndTime(endTime);
	
		applicationForEW.setEwId(ewId);
		applicationForEW.setEwState(ewState);
		applicationForEW.setRatifyId(ratifyId);
		

		
		return applicationForEW;
	}

}
